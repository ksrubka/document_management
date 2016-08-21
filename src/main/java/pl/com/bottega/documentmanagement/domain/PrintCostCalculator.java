package pl.com.bottega.documentmanagement.domain;

import java.math.BigDecimal;

/**
 * Created by Beata Iłowiecka on 20.08.2016.
 */
public interface PrintCostCalculator {

    BigDecimal calculateCost(int pagesCount);
}
