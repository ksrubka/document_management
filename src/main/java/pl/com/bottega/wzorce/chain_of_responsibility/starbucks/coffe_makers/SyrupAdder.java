package pl.com.bottega.wzorce.chain_of_responsibility.starbucks.coffe_makers;

import pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.Order;
import pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.syrup.Syrup;
import pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.syrup.SyrupFactory;

import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.CARAMEL_SYRUP;
import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.COCONUT_SYRUP;
import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.MAPLE_SYRUP;

public class SyrupAdder extends CoffeeMaker {

    @Override
    public void makeCoffee(Order order) {
        if (order.contains(MAPLE_SYRUP) || order.contains(CARAMEL_SYRUP) || order.contains(COCONUT_SYRUP)) {
            Syrup syrup = SyrupFactory.create(order);
            coffee.add(syrup.getName(), syrup.getCost());
        }
        nextCoffeeMaker.setCoffee(coffee);
        nextCoffeeMaker.makeCoffee(order);
    }
}
