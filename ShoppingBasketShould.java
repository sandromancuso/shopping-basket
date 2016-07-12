package com.codurance.craftingcode.exercise_10_shopping_cart;

import org.junit.Test;

import java.math.BigDecimal;

import static com.codurance.craftingcode.exercise_10_shopping_cart.ShoppingBasketBuilder.aShoppingBasket;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ShoppingBasketShould {

    public static final BigDecimal FORTY_FIVE = BigDecimal.valueOf(45.0);
    private static final ProductID PRODUCT_ID_1 = new ProductID("10001");
    private static final ProductID PRODUCT_ID_2 = new ProductID("20011");
    private static final int QTY_2 = 2;
    private static final BigDecimal TEN_POUNDS = BigDecimal.TEN;
    private static final int QTY_5 = 5;
    private static final BigDecimal FIVE_POUNDS = BigDecimal.valueOf(5);

    @Test public void
    return_the_total_cost() {
        ShoppingBasket shoppingBasket = aShoppingBasket()
                                            .withItem(PRODUCT_ID_1, QTY_2, TEN_POUNDS)
                                            .withItem(PRODUCT_ID_2, QTY_5, FIVE_POUNDS)
                                            .build();

        BigDecimal totalCost = shoppingBasket.total();

        assertThat(totalCost, is(FORTY_FIVE));
    } 

}