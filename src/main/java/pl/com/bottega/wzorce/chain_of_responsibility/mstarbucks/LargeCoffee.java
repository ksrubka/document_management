package pl.com.bottega.wzorce.chain_of_responsibility.mstarbucks;

import java.math.BigDecimal;

/**
 * Created by Beata Iłowiecka on 28.08.2016.
 */
public class LargeCoffee extends Ingredient {

    public LargeCoffee(Ingredient next) {
        super(next);
    }

    @Override
    protected BigDecimal ingredientCost() {
        return new BigDecimal(5.0);
    }

    @Override
    protected String ingredientName() {
        return " large coffee";
    }
}
