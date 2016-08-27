package pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.milk;

import java.math.BigDecimal;

class DoubleMilk extends AnyMilk {

    DoubleMilk() {
        name = ", double milk";
        cost = new BigDecimal(1.5);
    }
}
