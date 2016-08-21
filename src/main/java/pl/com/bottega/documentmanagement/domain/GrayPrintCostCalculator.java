package pl.com.bottega.documentmanagement.domain;

import java.math.BigDecimal;

/**
 * Created by Beata Iłowiecka on 20.08.2016.
 */
public class GrayPrintCostCalculator implements PrintCostCalculator {

    @Override
    public BigDecimal calculateCost(int pagesCount) {
        return new BigDecimal(pagesCount * 0.05);
    }
}
