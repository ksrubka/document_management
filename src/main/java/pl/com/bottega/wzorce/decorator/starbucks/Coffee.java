package pl.com.bottega.wzorce.decorator.starbucks;

import java.math.BigDecimal;

/**
 * Created by maciuch on 21.08.16.
 */
public interface Coffee {

    BigDecimal cost();
    String name();
}
