package pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.coffee;


import pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.Order;

import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.LARGE_COFFEE;
import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.MEDIUM_COFFEE;
import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.SMALL_COFFEE;

public class CoffeeFactory {
    public static Coffee create(Order order) {
        if (order.contains(SMALL_COFFEE)) {
            return new SmallCoffee();
        } else if (order.contains(MEDIUM_COFFEE)) {
            return new MediumCoffee();
        } else if (order.contains(LARGE_COFFEE)) {
            return new LargeCoffee();
        } else {
            throw new IllegalArgumentException("Something went wrong while I was making coffee.");
        }
    }
}
