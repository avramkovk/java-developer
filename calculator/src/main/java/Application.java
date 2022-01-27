import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calc = new Calculator();
        while (true) {
            try {
                String expression = scanner.nextLine();
                if (expression.equals("exit")) {
                    break;
                }
                System.out.println(calc.getSolution(expression));
            } catch (Exception e) {
                System.out.println("Wrong expression");
            }
        }
    }
}