package pl.com.bottega.wzorce.chain_of_responsibility.starbucks.coffe_makers;

import pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.Order;
import pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.coffee.Coffee;

public abstract class CoffeeMaker {

    protected Coffee coffee;

    public Coffee getCoffee() {
        return coffee;
    }

    void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public abstract void setNextCoffeeMaker(CoffeeMaker coffeeMaker);
    public abstract void makeCoffee(Order order);
}
