package pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.coffee;

import java.math.BigDecimal;

class SmallCoffee extends Coffee {

    SmallCoffee() {
        cost = new BigDecimal(5.0);
        name.append("Small coffee");
    }
}
