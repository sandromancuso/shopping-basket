package com.codurance.craftingcode.exercise_10_shopping_cart;

import java.math.BigDecimal;
import java.util.HashMap;

public class ProductService {

    public static final ProductId LOTR_ID = new ProductId("10001");
    public static final ProductId THE_HOBBIT_ID = new ProductId("10002");
    public static final ProductId GOT_S1_ID = new ProductId("20001");
    public static final ProductId GOT_S2_ID = new ProductId("20002");
    public static final ProductId BREAKING_BAD_S1_ID = new ProductId("20110");

    private static final HashMap<ProductId, BigDecimal> productPrices = new HashMap<ProductId, BigDecimal>(){
        {
            put(LOTR_ID, BigDecimal.valueOf(10));
            put(THE_HOBBIT_ID, BigDecimal.valueOf(5));
            put(GOT_S1_ID, BigDecimal.valueOf(10));
            put(GOT_S2_ID, BigDecimal.valueOf(10));
            put(BREAKING_BAD_S1_ID, BigDecimal.valueOf(7));
        }
    };

    public BigDecimal priceFor(ProductId productId) {
        return productPrices.get(productId);
    }
}
