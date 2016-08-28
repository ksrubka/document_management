package pl.com.bottega.wzorce.chain_of_responsibility.mstarbucks;

import java.math.BigDecimal;

/**
 * Created by Beata Iłowiecka on 28.08.2016.
 */
public class CoffeeMaker {

    private Ingredient firstIngredient;

    public CoffeeMaker(Ingredient firstIngredient) {
        this.firstIngredient = firstIngredient;
    }

    public BigDecimal cost() {
        return firstIngredient.cost();
    }

    public String name() {
        return firstIngredient.name();
    }

    public static void main(String[] args) {
        CoffeeMaker c1 = new CoffeeMaker(new SmallCoffee(new Milk(null)));
        CoffeeMaker c2 = new CoffeeMaker(new LargeCoffee(null));
        CoffeeMaker c3 = new CoffeeMaker(new LargeCoffee(new Milk(null)));

        System.out.println(c1.name() + " : " + c1.cost());
        System.out.println(c2.name() + " : " + c3.cost());
        System.out.println(c3.name() + " : " + c3.cost());
    }
}
