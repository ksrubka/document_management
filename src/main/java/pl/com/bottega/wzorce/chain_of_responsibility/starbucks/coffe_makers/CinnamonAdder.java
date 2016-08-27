package pl.com.bottega.wzorce.chain_of_responsibility.starbucks.coffe_makers;

import pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.Order;

import java.math.BigDecimal;

import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.CINNAMON;

public class CinnamonAdder extends CoffeeMaker {

    private static final BigDecimal CINNAMON_COST = new BigDecimal(0.5);
    private static final String CINNAMON_NAME = ", cinnamon";

    private CoffeeMaker nextCoffeeMaker;

    @Override
    public void setNextCoffeeMaker(CoffeeMaker coffeeMaker) {
        this.nextCoffeeMaker = coffeeMaker;
    }

    @Override
    public void makeCoffee(Order order) {
        if (order.contains(CINNAMON)) {
            coffee.add(CINNAMON_NAME, CINNAMON_COST);
        }
        nextCoffeeMaker.setCoffee(coffee);
        nextCoffeeMaker.makeCoffee(order);
    }
}
