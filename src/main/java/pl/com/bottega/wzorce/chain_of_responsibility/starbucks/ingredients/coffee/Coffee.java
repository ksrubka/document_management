package pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.coffee;

import java.math.BigDecimal;

public class Coffee {

    protected StringBuilder name = new StringBuilder();
    protected BigDecimal cost = new BigDecimal(0);

    @Override
    public String toString() {
        return "Coffee{" +
                "cost=" + cost +
                ", name='" + name + '\'' +
                '}';
    }

    public void add(String ingredientName, BigDecimal ingredientCost) {
        name = name.append(ingredientName);
        cost = cost.add(ingredientCost);
    }
}
