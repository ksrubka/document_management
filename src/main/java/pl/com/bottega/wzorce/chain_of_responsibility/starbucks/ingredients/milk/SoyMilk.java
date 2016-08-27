package pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.milk;

import java.math.BigDecimal;

class SoyMilk extends AnyMilk {
    SoyMilk() {
        name = ", milk";
        cost = new BigDecimal(1.5);
    }
}