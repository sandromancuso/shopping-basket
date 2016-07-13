package com.codurance.shoppingbasket;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PriceService {

    private static final ProductID LOTR = new ProductID("10001");
    private static final ProductID THE_HOBBIT = new ProductID("10002");
    private static final ProductID GAME_OF_THRONES = new ProductID("20001");
    private static final ProductID BREAKING_BAD = new ProductID("20110");

    private static final BigDecimal TEN_POUNDS = BigDecimal.valueOf(10.0);
    private static final BigDecimal FIVE_POUNDS = BigDecimal.valueOf(5.0);
    private static final BigDecimal NINE_POUNDS = BigDecimal.valueOf(9.0);
    private static final BigDecimal SEVEN_POUNDS = BigDecimal.valueOf(7.0);

    private static Map<ProductID, BigDecimal> prices = new HashMap<ProductID, BigDecimal>() {
        {
            put(LOTR, TEN_POUNDS);
            put(THE_HOBBIT, FIVE_POUNDS);
            put(GAME_OF_THRONES, NINE_POUNDS);
            put(BREAKING_BAD, SEVEN_POUNDS);
        }
    };

    public BigDecimal priceFor(ProductID productId) {
        return prices.get(productId);
    }
}
