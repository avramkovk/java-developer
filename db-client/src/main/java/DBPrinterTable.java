import de.vandermeer.asciitable.AsciiTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBPrinterTable {
    private static final Logger LOGGER = Logger.getLogger(DBPrinterTable.class.getName());

    public static boolean  selectAndPrintAll(Connection conn, String query) {
        AsciiTable asciiTable = new AsciiTable(); //create a table
        try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery();) {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            List<String> columnNames = new ArrayList<>();
            for (int j = 1; j <= columnCount; j++) {
                columnNames.add(rsmd.getColumnName(j));
            }

            asciiTable.addRule();                //draw line
            asciiTable.addRow(columnNames); //add content: column name in table
            asciiTable.addRule();               //draw line

            while (rs.next()) {
                List<Object> rowValue = new ArrayList<>();
                for (int j = 1; j <= columnCount; j++) {
                    rowValue.add(rs.getObject(j));
                }
                asciiTable.addRow(rowValue);
            }
            asciiTable.addRule();
            String renderTable = asciiTable.render();
            System.out.println(renderTable);
            return pstmt.execute();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE,"failed to select and print all: ",e);
            return false;
        }
    }
}