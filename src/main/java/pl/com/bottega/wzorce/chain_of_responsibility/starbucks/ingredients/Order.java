package pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Order {

    private Set<CoffeeIngredients> ingredients = new HashSet<>();

    public Order(CoffeeIngredients... ingredients) {
        Collections.addAll(this.ingredients, ingredients);
    }

    public boolean contains(CoffeeIngredients ingredient) {
        return ingredients.contains(ingredient);
    }
}
