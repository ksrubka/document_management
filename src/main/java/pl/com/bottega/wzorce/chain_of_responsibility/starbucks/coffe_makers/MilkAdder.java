package pl.com.bottega.wzorce.chain_of_responsibility.starbucks.coffe_makers;

import pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.Order;
import pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.milk.AnyMilk;
import pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.milk.MilkFactory;

import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.DOUBLE_MILK;
import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.MILK;
import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.SOY_MILK;

public class MilkAdder extends CoffeeMaker {

    private CoffeeMaker nextCoffeeMaker;

    public void setNextCoffeeMaker(CoffeeMaker coffeeMaker) {
        this.nextCoffeeMaker = coffeeMaker;
    }

    @Override
    public void makeCoffee(Order order) {
        if (orderHasMilk(order)) {
            AnyMilk milk = MilkFactory.create(order);
            coffee.add(milk.getName(), milk.getCost());
            }
        nextCoffeeMaker.setCoffee(coffee);
        nextCoffeeMaker.makeCoffee(order);
    }

    private boolean orderHasMilk(Order order) {
        return order.contains(MILK) || order.contains(DOUBLE_MILK) || order.contains(SOY_MILK);
    }
}
