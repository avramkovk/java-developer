import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CoffeeMachine {
    private static DBCoffeeMachine dbCoffeeMachine = new DBCoffeeMachine();
    private Map<String, Integer> remainings;
    private List<Coffee> drinks;
    private Map<String,String> productsItems;

    public CoffeeMachine() {
        init();
    }

    public void init() {
        drinks = dbCoffeeMachine.getDrinks();
        remainings = dbCoffeeMachine.getRemainings();
        productsItems=dbCoffeeMachine.getProductsItem();
    }

    public Map<String,String> getProductsItems(){
        return productsItems;
    }
    public Map<String, Integer> getRemaining() {
        return remainings;
    }

    public int takeCash() {
        int cash = dbCoffeeMachine.takeCash();
        remainings = dbCoffeeMachine.getRemainings();
        return cash;
    }

    public void buy(Coffee coffee) throws NotEnoughIngredientsException {
        checkIngredientsFor(coffee);
        changeQuantityProducts(coffee);
    }

    private void checkIngredientsFor(Coffee coffee) throws NotEnoughIngredientsException {
        List<String> listFailureOrder = new ArrayList<>();
        for (Map.Entry<String, Integer> component : coffee.getComponents().entrySet()) {
            int remaining = remainings.get(component.getKey()) - component.getValue();
            if (remaining < 0) {
                listFailureOrder.add(component.getKey());
            }
        }
        if (listFailureOrder.size() > 0) {
            throw new NotEnoughIngredientsException(listFailureOrder);
        }
    }

    private void changeQuantityProducts(Coffee coffee) throws NotEnoughIngredientsException {
        if (!dbCoffeeMachine.updateRemainings(coffee.getComponents())) {
            throw new NotEnoughIngredientsException(Arrays.asList("ingredients"));
        }
        remainings = dbCoffeeMachine.getRemainings();
    }

    public void updateQuantityProduct(String product, int quantity) {
        dbCoffeeMachine.updateQuantityProduct(product, quantity);
        remainings = dbCoffeeMachine.getRemainings();
    }

    public Coffee[] getDrinks() {
        return drinks.toArray(new Coffee[0]);
    }
}