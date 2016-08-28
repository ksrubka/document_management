package pl.com.bottega.wzorce.chain_of_responsibility.mstarbucks;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;

/**
 * Created by Beata Iłowiecka on 28.08.2016.
 */
public class Milk extends Ingredient {

    public Milk(Ingredient next) {
        super(next);
    }

    @Override
    protected BigDecimal ingredientCost() {
        return new BigDecimal(0.5);
    }

    @Override
    protected String ingredientName() {
        return " milk";
    }
}
