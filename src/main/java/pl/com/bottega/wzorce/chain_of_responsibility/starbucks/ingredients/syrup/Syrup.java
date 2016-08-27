package pl.com.bottega.wzorce.chain_of_responsibility.starbucks.ingredients.syrup;

import java.math.BigDecimal;

public class Syrup {

    protected String name;
    private BigDecimal cost = new BigDecimal(2.0);

    public String getName() {
        return name;
    }

    public BigDecimal getCost() {
        return cost;
    }
}
