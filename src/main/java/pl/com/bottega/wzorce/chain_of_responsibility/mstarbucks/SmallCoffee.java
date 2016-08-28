package pl.com.bottega.wzorce.chain_of_responsibility.mstarbucks;

import java.math.BigDecimal;

/**
 * Created by Beata Iłowiecka on 28.08.2016.
 */
public class SmallCoffee extends Ingredient {

    public SmallCoffee(Ingredient next) {
        super(next);
    }

    @Override
    protected BigDecimal ingredientCost() {
        return new BigDecimal(2.5);
    }

    @Override
    protected String ingredientName() {
        return " small coffee";
    }
}
