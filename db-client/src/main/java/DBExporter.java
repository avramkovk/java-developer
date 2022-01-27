import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBExporter {
    private static final Logger LOGGER = Logger.getLogger(DBExporter.class.getName());

    public boolean exportToCSV(Connection conn, PrintWriter writer, String sql) {
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            writeAsCSV(writer, rs);
            return stmt.execute(sql);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception: ", e);
            return false;
        }
    }

    private void writeAsCSV(PrintWriter writer, ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int countColumns = rsmd.getColumnCount();
            for (int j = 1; j <= countColumns; j++) {
                writer.print("\"" + rsmd.getColumnName(j) + "\"");
                if (j == countColumns) {
                    writer.println();
                    break;
                }
                writer.print(",");
            }

            while (rs.next()) {
                for (int j = 1; j <= countColumns; j++) {
                    writer.print("\"" + rs.getObject(j) + "\"");
                    if (j == countColumns) {
                        break;
                    }
                    writer.print(",");
                }
                writer.println();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception: ", e);
        }
    }

    public void executeDDL(Connection conn, String query) {
        try (Statement stmt = conn.createStatement();) {
            stmt.execute(query);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception: ", e);
        }
    }

    public int executeDML(Connection conn, String query) {
        try (PreparedStatement pstmtInsertUpdateDelete = conn.prepareStatement(query)) {
            return pstmtInsertUpdateDelete.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception: ", e);
            return 0;
        }
    }
}