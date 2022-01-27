import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DB {

    private static final String URL = "jdbc:postgresql://localhost/postgres?user=postgres&password=12345";
    private static Connection connection;

    public DB() {
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initGame() {
        createTableCapitals();
        insertCapitals();
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableCapitals() {
        String query = "CREATE TABLE IF NOT EXISTS capitals(country TEXT NOT NULL, city TEXT NOT NULL)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertCapitals() {
        String query = "INSERT INTO capitals(country, city) values(?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, "Belarus");
            pstmt.setString(2, "Minsk");
            pstmt.addBatch();
            pstmt.setString(1, "Poland");
            pstmt.setString(2, "Warsaw");
            pstmt.addBatch();
            pstmt.setString(1, "Russia");
            pstmt.setString(2, "Moscow");
            pstmt.addBatch();
            pstmt.setString(1, "Afghanistan");
            pstmt.setString(2, "Kabul");
            pstmt.addBatch();
            pstmt.setString(1, "Bulgaria");
            pstmt.setString(2, "Sofia");
            pstmt.addBatch();
            pstmt.setString(1, "Canada");
            pstmt.setString(2, "Ottawa");
            pstmt.addBatch();
            pstmt.setString(1, "Cuba");
            pstmt.setString(2, "Havana");
            pstmt.addBatch();
            pstmt.setString(1, "France");
            pstmt.setString(2, "Paris");
            pstmt.addBatch();
            pstmt.setString(1, "Germany");
            pstmt.setString(2, "Berlin");
            pstmt.addBatch();
            pstmt.setString(1, "Japan");
            pstmt.setString(2, "Tokyo");
            pstmt.addBatch();
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getRandomCountry() {
        String query = "SELECT country FROM capitals ORDER BY RANDOM() LIMIT 1";
        String randomCity = "";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                randomCity = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return randomCity;
    }

    public String getCapital(String country) {
        String query = "SELECT city FROM capitals WHERE country = '" + country + "'";
        String randomCity = "";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                randomCity = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return randomCity;
    }

}