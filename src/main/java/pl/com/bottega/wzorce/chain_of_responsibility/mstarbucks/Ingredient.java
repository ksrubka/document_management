package pl.com.bottega.wzorce.chain_of_responsibility.mstarbucks;

import java.math.BigDecimal;

/**
 * Created by Beata Iłowiecka on 28.08.2016.
 */
public abstract class Ingredient {

    private Ingredient next;

    public Ingredient(Ingredient next) {
        this.next = next;
    }

    public BigDecimal cost() {
        if (next != null) {
            return next.cost().add(ingredientCost());
        }
        else {
            return ingredientCost();
        }
    }

    public String name() {
        if (next != null) {
            return ingredientName() + " with " + next.name();
        }
        else {
            return ingredientName();
        }
    }

    protected abstract BigDecimal ingredientCost();

    protected abstract String ingredientName();


}
