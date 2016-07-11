package com.codurance.craftingcode.exercise_10_shopping_cart;

import org.junit.Test;

import java.math.BigDecimal;

import static com.codurance.craftingcode.exercise_10_shopping_cart.ProductService.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProductServiceShould {

    private ProductService productService = new ProductService();

    @Test
    public void
    return_price_for_products() {
        assertThat(productService.priceFor(LOTR_ID), is(BigDecimal.valueOf(10)));
        assertThat(productService.priceFor(THE_HOBBIT_ID), is(BigDecimal.valueOf(5)));
        assertThat(productService.priceFor(GOT_S1_ID), is(BigDecimal.valueOf(10)));
        assertThat(productService.priceFor(GOT_S2_ID), is(BigDecimal.valueOf(10)));
        assertThat(productService.priceFor(BREAKING_BAD_S1_ID), is(BigDecimal.valueOf(7)));
    }

}