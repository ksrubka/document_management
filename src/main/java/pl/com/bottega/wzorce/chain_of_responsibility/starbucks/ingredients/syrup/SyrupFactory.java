package pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.syrup;

import pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.Order;

import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.CARAMEL_SYRUP;
import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.COCONUT_SYRUP;
import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.MAPLE_SYRUP;

public class SyrupFactory {

    public static Syrup create(Order order) {
        if (order.contains(MAPLE_SYRUP)) {
            return new MapleSyrup();
        }
        else if (order.contains(COCONUT_SYRUP)) {
            return new CoconutSyrup();
        }
        else if (order.contains(CARAMEL_SYRUP)) {
            return new CaramelSyrup();
        }
        else {
            throw new IllegalArgumentException("Something went wrong while I was adding syrup.");
        }
    }
}
