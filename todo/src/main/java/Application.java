import de.vandermeer.asciitable.AsciiTable;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    private static DBScheduler dbSheduler = new DBScheduler();
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("^[0-9]+");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        try {
            while (true) {
                try {
                    input = scanner.nextLine();
                    String[] arguments = input.split(" ", 2);
                    String command = arguments[0].toLowerCase();
                    String params = arguments.length == 2 ? arguments[1] : "";
                    switch (command) {
                        case "exit":
                            return;
                        case "new":
                            if (params.isEmpty()) {
                                System.out.println("no task name");
                                continue;
                            }
                            createNewTask(params);
                            break;
                        case "list":
                            printTasks(params);
                            break;
                        case "done":
                            markAsDone(params);
                            break;
                        default:
                            System.out.println("Invalid command.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } finally {
            close(scanner, dbSheduler);
        }
    }

    private static void close(Closeable... resources) {
        for (Closeable resource : resources) {
            try {
                resource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createNewTask(String input) {
        int idTask = dbSheduler.createTask(input);
        System.out.printf("task #%s created\n", idTask);
    }

    private static void printTasks(String input) {
        AsciiTable asciiTable = new AsciiTable(); //create a table
        asciiTable.addRule();                //draw line
        asciiTable.addRow("task", "name"); //add content: column name in table
        asciiTable.addRule();               //draw line

        switch (input) {
            case "":
                List<Task> listInProgress = dbSheduler.getTasksInProgress();
                for (Task task : listInProgress) {
                    asciiTable.addRow(task.getId(), task.getName());
                }
                break;
            case "--done":
                List<Task> taskDone = dbSheduler.getTasksDone();
                for (Task task : taskDone) {
                    asciiTable.addRow(task.getId(), task.getName());
                }
                break;
            default:
                System.out.println("Invalid command.");
        }
        asciiTable.addRule();               //draw line
        String drawTable = asciiTable.render();
        System.out.println(drawTable);
    }

    public static void markAsDone(String input) {
        Matcher matcher = NUMERIC_PATTERN.matcher(input);
        if (!matcher.find()) {
            System.out.println("please enter task number");
            return;
        }

        int idTask = Integer.parseInt(matcher.group());
        if (dbSheduler.markAsDone(idTask)) {
            System.out.printf("task %s done\n", idTask);
        } else {
            System.out.printf("task #%s does not exist \"in progress\"\n", idTask);
        }
    }
}