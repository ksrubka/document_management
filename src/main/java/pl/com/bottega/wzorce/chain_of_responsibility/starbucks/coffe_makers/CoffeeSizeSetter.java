package pl.com.bottega.wzorce.chain_of_responsibility.starbucks.coffe_makers;

import pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.Order;
import pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.coffee.CoffeeFactory;

import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.LARGE_COFFEE;
import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.MEDIUM_COFFEE;
import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.SMALL_COFFEE;

public class CoffeeSizeSetter extends CoffeeMaker {

    @Override
    public void makeCoffee(Order order) {
        if (order.contains(SMALL_COFFEE) ||
                order.contains(MEDIUM_COFFEE) || order.contains(LARGE_COFFEE)) {
            coffee = CoffeeFactory.create(order);
            nextCoffeeMaker.setCoffee(coffee);
            nextCoffeeMaker.makeCoffee(order);
        }
        else {
            System.out.println("Coffee size not specified.");
        }
    }
}
