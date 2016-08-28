package pl.com.bottega.wzorce.chain_of_responsibility.starbucks.coffe_makers;

import pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.Order;

import java.math.BigDecimal;

import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.ICE;

public class IceAdder extends CoffeeMaker {

    private static final BigDecimal ICE_COST = new BigDecimal(0.5);
    private static final String ICE_NAME = ", ice";

    @Override
    public void makeCoffee(Order order) {
        if (order.contains(ICE)) {
            coffee.add(ICE_NAME, ICE_COST);
        }
        nextCoffeeMaker.setCoffee(coffee);
        nextCoffeeMaker.makeCoffee(order);
    }
}
