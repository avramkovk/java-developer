import java.util.Map;
import java.util.Scanner;

public class Menu {
    private CoffeeMachine coffeeMachine = new CoffeeMachine();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //new DBCoffeeMachine().initTestTables();
        new Menu().menuChoice();
    }

    public void menuChoice() {
        String inputString = "";
        while (!inputString.equals("exit")) {
            System.out.println("Write action (buy, fill, take, remaining, exit)");
            inputString = scanner.next();
            switch (inputString) {
                case "buy":
                    try {
                        buyCoffee();
                    } catch (NotEnoughIngredientsException e) {
                        System.out.println("Sorry, not enough " + String.join(", ", e.getIngredients()) + "!");
                    }
                    break;
                case "fill":
                    fillProducts();
                    break;
                case "take":
                    takeMoney();
                    break;
                case "remaining":
                    remaining();
                    break;
                default:
                    System.out.println("usage: buy, fill, take, remaining, exit");
            }
        }
        scanner.close();
    }

    public void buyCoffee() throws NotEnoughIngredientsException {
        String inputString = "";
        System.out.print("What do you want to buy?");
        int i = 1;
        for (Coffee coffee : coffeeMachine.getDrinks()) {
            System.out.printf(" %s - %s", i, coffee.getName());
            i++;
        }
        System.out.println(" back - to main menu");
        inputString = scanner.next();
        switch (inputString) {
            case "back":
                return;
            default:
                if (!isNumber(inputString)) {
                    System.out.println("Incorrect choice");
                    return;
                }
                int coffeeIndex = Integer.parseInt(inputString);
                if (coffeeIndex < 1 || coffeeIndex > coffeeMachine.getDrinks().length) {
                    System.out.println("Incorrect choice");
                    return;
                }
                coffeeMachine.buy(coffeeMachine.getDrinks()[coffeeIndex - 1]);
        }
        System.out.println("I have enough resources, making you a coffee!");
    }

    private static boolean isNumber(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void remaining() {
        Map<String, Integer> remaining = coffeeMachine.getRemaining();
        Map<String, String> items = coffeeMachine.getProductsItems();

        System.out.println("The coffee machine has:");
        for (Map.Entry<String, Integer> product : remaining.entrySet()) {
            System.out.printf("%s %s %s\n", product.getKey(), product.getValue(), items.get(product.getKey()));
        }
    }

    public void takeMoney() {
        System.out.println("I gave you $" + coffeeMachine.takeCash());
    }

    public void fillProducts() {
        Map<String, Integer> remaining = coffeeMachine.getRemaining();
        Map<String, String> item = coffeeMachine.getProductsItems();
        for (Map.Entry<String, Integer> product : remaining.entrySet()) {
            if (product.getKey().equals("cash")) {
                continue;
            }
            System.out.printf("Write how many %s of %s do you want to add:\n", item.get(product.getKey()), product.getKey());
            if (!scanner.hasNextInt() && scanner.nextInt() < 0) {
                System.out.println("incorrect quantity " + product.getKey());
                return;
            }
            int quantityProduct = scanner.nextInt();
            coffeeMachine.updateQuantityProduct(product.getKey(), quantityProduct);
        }
    }
}
