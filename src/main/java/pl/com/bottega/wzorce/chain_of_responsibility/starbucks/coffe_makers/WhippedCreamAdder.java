package pl.com.bottega.wzorce.chain_of_responsibility.starbucks.coffe_makers;

import pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.Order;

import java.math.BigDecimal;

import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.WHIPPED_CREAM;

public class WhippedCreamAdder extends CoffeeMaker {

    private static final BigDecimal WHIPPED_CREAM_COST = new BigDecimal(1.0);
    private static final String WHIPPED_CREAM_NAME = ", whipped cream";

    private CoffeeMaker nextCoffeeMaker;

    public void setNextCoffeeMaker(CoffeeMaker coffeeMaker) {
        this.nextCoffeeMaker = coffeeMaker;
    }

    @Override
    public void makeCoffee(Order order) {
        if (order.contains(WHIPPED_CREAM)) {
            coffee.add(WHIPPED_CREAM_NAME, WHIPPED_CREAM_COST);
        }
    }
}
