package pl.com.bottega.wzorce.chain_of_responsibility.starbucks;

import pl.com.bottega.wzorce.chain_of_responsibility.starbucks.coffe_makers.*;
import pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.Order;

import static pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.CoffeeIngredients.*;

public class StarbucksApp {

    public static void main(String[] args) {
        //****************************************************
        // Create coffee makers
        CoffeeMaker coffeeMaker1 = new CoffeeSizeSetter();
        CoffeeMaker milkAdder2 = new MilkAdder();
        CoffeeMaker iceAdder3 = new IceAdder();
        CoffeeMaker syrupAdder4 = new SyrupAdder();
        CoffeeMaker cinnamonAdder5 = new CinnamonAdder();
        CoffeeMaker whippedCreamAdder6 = new WhippedCreamAdder();

        // Set order of adding ingredients
        coffeeMaker1.setNextCoffeeMaker(milkAdder2);
        milkAdder2.setNextCoffeeMaker(iceAdder3);
        iceAdder3.setNextCoffeeMaker(syrupAdder4);
        syrupAdder4.setNextCoffeeMaker(cinnamonAdder5);
        cinnamonAdder5.setNextCoffeeMaker(whippedCreamAdder6);

        //*****************************************************

        System.out.println("First order:");
        Order order1 = new Order(SMALL_COFFEE, MILK, ICE, MAPLE_SYRUP, CINNAMON, WHIPPED_CREAM);
        coffeeMaker1.makeCoffee(order1);
        String coffeeDescription1 = coffeeMaker1.getCoffee().toString();
        System.out.println(coffeeDescription1 + "\n");

        System.out.println("Second order:");
        Order order2 = new Order(LARGE_COFFEE, WHIPPED_CREAM);
        coffeeMaker1.makeCoffee(order2);
        String coffeeDescription2 = coffeeMaker1.getCoffee().toString();
        System.out.println(coffeeDescription2 + "\n");

        System.out.println("Third order:");
        Order order3 = new Order(MAPLE_SYRUP);
        coffeeMaker1.makeCoffee(order3);

        System.out.println("\n" + "Fourth order:");
        Order order4 = new Order(ICE, CINNAMON, CARAMEL_SYRUP, MEDIUM_COFFEE);
        coffeeMaker1.makeCoffee(order4);
        String coffeeDescription4 = coffeeMaker1.getCoffee().toString();
        System.out.println(coffeeDescription4);
    }
}
