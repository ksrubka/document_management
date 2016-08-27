package pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.milk;

import pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.Order;

import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.DOUBLE_MILK;
import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.MILK;
import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.SOY_MILK;

public class MilkFactory {
    public static AnyMilk create(Order order) {
        if (order.contains(MILK)) {
            return new Milk();
        }
        else if (order.contains(DOUBLE_MILK)) {
            return new DoubleMilk();
        }
        else if (order.contains(SOY_MILK)) {
            return new SoyMilk();
        }
        throw new IllegalArgumentException("Something went wrong while I was adding milk.");
    }
}