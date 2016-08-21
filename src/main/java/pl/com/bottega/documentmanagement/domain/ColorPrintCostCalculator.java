package pl.com.bottega.documentmanagement.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ColorPrintCostCalculator implements PrintCostCalculator {

    @Override
    public BigDecimal calculateCost(int pagesCount) {
        return new BigDecimal(pagesCount * 0.1);
    }
}
