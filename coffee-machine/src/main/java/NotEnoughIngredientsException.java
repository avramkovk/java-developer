import java.util.Collections;
import java.util.List;

public class NotEnoughIngredientsException extends Exception {
    private List<String> ingredients;

    public NotEnoughIngredientsException(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getIngredients() {
        return Collections.unmodifiableList(ingredients);
    }
}
