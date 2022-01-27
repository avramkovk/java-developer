import java.sql.*;

public class JdbcApi {
    private static final String URL = "jdbc:postgresql://localhost/postgres?user=postgres&password=12345";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            try {
                conn.setAutoCommit(false);
                createTableIfNotExists(conn);
                insertIntoTable(conn, "Opel", "Corsa", 2016);
                insertIntoTable(conn, "UAZ", "Combi", 2016);
                insertIntoTable(conn, "Renault ", "Arkana", 2016);
                selectAndPrintAll(conn);
                conn.commit();
            } catch (Exception e) {
                System.err.println("Transaction is rolled back");
                conn.rollback();
            }
        } catch (SQLException e) {
            System.err.println("Impossible to create connection");
        }
    }

    private static void insertIntoTable(Connection conn, String carName, String carModel, int carYear) {
        String query = "insert into cars values(?,?,?);";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, carName);
            pstmt.setString(2, carModel);
            pstmt.setInt(3, carYear);
            int inserted = pstmt.executeUpdate();
            System.out.println("added rows: " + inserted);
        } catch (SQLException e) {
            throw new IllegalStateException("Fail to insert values to table", e);
        }
    }

    private static void createTableIfNotExists(Connection conn) {
        String query = "create table if not exists cars(name text not null, model text not null, year integer not null)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.execute();
        } catch (SQLException e) {
            throw new IllegalStateException("Fail to create table", e);
        }
    }

    private static void selectAndPrintAll(Connection conn) {
        String query = "SELECT name, model, year FROM cars";
        try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
            System.out.format("+--------------+------------+------+%n");
            System.out.format("| name         | model      | year |%n");
            System.out.format("+--------------+------------+------+%n");
            while (rs.next()) {
                String name = rs.getString(1);
                String model = rs.getString(2);
                int year = rs.getInt(3);
                String leftAlignFormat = "| %-12s | %-10s | %-4s |%n";
                System.out.format(leftAlignFormat, name, model, year);
            }
            System.out.format("+--------------+------------+------+%n");
        } catch (SQLException e) {
            throw new IllegalStateException("Fail to select values from table", e);
        }
    }
}