import java.util.Scanner;

public class GuessCapitals {
    private static int counterQuestions = 0;
    private static int counterCorrectAnswers = 0;

    public static void main(String[] args) {
        //new DB().initGame();
        GuessCapitals game = new GuessCapitals();
        DB db = new DB();
        Scanner scanner = new Scanner(System.in);
        game.start(scanner, db);
        db.closeConnection();
        scanner.close();
    }

    private void guessCapitals(Scanner scanner, DB db) {
        String country = db.getRandomCountry();
        String capital = db.getCapital(country);
        counterQuestions++;
        System.out.printf("%s. Enter the capital of %s\n", counterQuestions, country);
        String answer = scanner.next();
        if (answer.equals(capital)) {
            counterCorrectAnswers++;
            System.out.println("Correct answer");
        } else {
            System.out.println("Incorrect answer");
        }
    }

    private void showResult() {
        System.out.printf("\nYour score is: %s/%s\n", counterCorrectAnswers, counterQuestions);
    }

    public void start(Scanner scanner, DB db) {
        while (true) {
            System.out.println("Hi, welcome to \"Guess capitals\" game.");
            System.out.println("Enter, how many questions you want to answer:");
            int quantityQuestions = scanner.nextInt();
            for (int i = 0; i < quantityQuestions; i++) {
                guessCapitals(scanner, db);
            }
            showResult();
            System.out.println("If you want to restart, enter \"play\".");
            String restartCommand = "play";
            String command = scanner.next();
            if (command.equalsIgnoreCase(restartCommand)) {
                counterQuestions = 0;
                counterCorrectAnswers = 0;
            } else {
                System.out.println("Thanks for playing game \"Guess capitals\".");
                System.out.println("Come back any time!");
                return;
            }
        }
    }
}