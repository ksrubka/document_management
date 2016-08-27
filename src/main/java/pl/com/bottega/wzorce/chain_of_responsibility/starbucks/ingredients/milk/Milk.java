package pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.milk;

import java.math.BigDecimal;

class Milk extends AnyMilk {
    Milk() {
        name = ", milk";
        cost = new BigDecimal(1.0);
    }
}