import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class DBClient {
    private static final Logger LOGGER = Logger.getLogger(DBClient.class.getName());
    private static final DBExporter DB_EXPORTER = new DBExporter();
    private static final File FILE_EXPORT = new File("result.csv");
    private static final String URL = "jdbc:postgresql://localhost/postgres?user=postgres&password=12345";

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("db-client/log.properties"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "failed to read logger configuration:", e);
        }

        Scanner scanner = new Scanner(System.in);
        try (Connection conn = DriverManager.getConnection(URL)) {
            while (true) {
                String input = scanner.nextLine();
                String[] arguments = input.split(" ", 2);
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }
                if (arguments.length == 2) {
                    processCommand(conn, input);
                } else {
                    printHelpOnCommand();
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "failed to process command: ", e);
        } finally {
            closeResources(scanner);
        }
    }

    private static void processCommand(Connection conn, String arguments) {
        String command = arguments.split(" ", 2)[0].toUpperCase();
        String commandToExport = arguments.toUpperCase().split(" ", 2)[1];
        switch (command) {
            case "SELECT":
                printTable(conn, arguments);
                break;
            case "INSERT":
            case "UPDATE":
            case "DELETE":
                executeDML(conn, arguments);
                break;
            case "CREATE":
            case "ALTER":
            case "DROP":
                executeDDL(conn, arguments);
                break;
            case "EXPORT":
                exportToCSV(conn, commandToExport);
                break;
            default:
                printHelpOnCommand();
        }
    }

    private static void closeResources(Closeable... resources) {
        for (Closeable resource : resources) {
            try {
                resource.close();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Exception: ", e);
            }
        }
    }

    private static void printTable(Connection conn, String arguments) {
        if (!DBPrinterTable.selectAndPrintAll(conn, arguments)) {
            System.out.println("invalid query with SELECT");
        }
    }

    private static void executeDML(Connection conn, String sql) {
        int updatedRows = DB_EXPORTER.executeDML(conn, sql);
        System.out.println("updatedRows rows: " + updatedRows);
    }

    private static void executeDDL(Connection conn, String arguments) {
        DB_EXPORTER.executeDDL(conn, arguments);
    }

    private static void exportToCSV(Connection conn, String query) {
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(FILE_EXPORT)))) {
            String[] arguments = query.trim().split(" ", 2);
            String command = arguments.length == 2 ? arguments[0] : "";
            switch (command) {
                case "SELECT":
                case "TABLE":
                    executeExportQuery(conn, printWriter, query, arguments);
                    break;
                default:
                    printHelpOnCommand();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception: ", e);
        }
    }

    private static void executeExportQuery(Connection conn, PrintWriter printWriter, String query, String[] arguments) {
        if (arguments[1].isEmpty()) {
            printHelpOnCommand();
            return;
        }
        printExportMessage(DB_EXPORTER.exportToCSV(conn, printWriter, query));
    }

    private static void printExportMessage(boolean exportSuccess) {
        if (exportSuccess) {
            System.out.println("exported to " + FILE_EXPORT);
        } else {
            System.out.println("export failed");
        }
    }

    private static void printHelpOnCommand() {
        System.out.println("usage:");
        System.out.println("SELECT,INSERT,UPDATE,DELETE,CREATE,ALTER,DROP — sql query execution.");
        System.out.println("'export table tableName' or 'export SELECT * FROM tableName' — export data from table");
    }
}