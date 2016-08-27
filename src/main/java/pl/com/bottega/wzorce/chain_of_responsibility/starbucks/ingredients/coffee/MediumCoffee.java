package pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.coffee;


import java.math.BigDecimal;

class MediumCoffee extends Coffee {

    MediumCoffee() {
        cost = new BigDecimal(6.0);
        name.append("Medium coffee");
    }
}
