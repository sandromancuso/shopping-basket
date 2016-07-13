package com.codurance.shoppingbasket;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriceServiceShould {
    private static final ProductID LOTR = new ProductID("10001");
    private static final ProductID THE_HOBBIT_ID = new ProductID("10002");
    private static final ProductID GAME_OF_THRONES = new ProductID("20001");
    private static final ProductID BREAKING_BAD = new ProductID("20110");

    private static final BigDecimal TEN_POUNDS = BigDecimal.valueOf(10.0);
    private static final BigDecimal FIVE_POUNDS = BigDecimal.valueOf(5.0);
    private static final BigDecimal NINE_POUNDS = BigDecimal.valueOf(9.0);
    private static final BigDecimal SEVEN_POUNDS = BigDecimal.valueOf(7.0);

    private PriceService priceSerivce;

    @Before
    public void initialise() {
        priceSerivce = new PriceService();
    }

    @Test public void
    return_price_for_all_available_products() {
        assertThat(priceFor(LOTR), is(TEN_POUNDS));
        assertThat(priceFor(THE_HOBBIT_ID), is(FIVE_POUNDS));
        assertThat(priceFor(GAME_OF_THRONES), is(NINE_POUNDS));
        assertThat(priceFor(BREAKING_BAD), is(SEVEN_POUNDS));
    }

    private BigDecimal priceFor(ProductID productID) {
        return priceSerivce.priceFor(productID);
    }

}