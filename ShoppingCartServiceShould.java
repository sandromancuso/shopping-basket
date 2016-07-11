package com.codurance.craftingcode.exercise_10_shopping_cart;

import com.codurance.craftingcode.exercise_05_vanila_bank_kata.Clock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartServiceShould {

    private static final UserId USER_ID = new UserId("U1111");
    private static final ProductId PRODUCT_ID = new ProductId("P0012");
    private static final int QUANTITY = 10;
    public static final int QUANTITY_10 = 10;

    @Mock ShoppingCartRepository shoppingCartRepository;
    @Mock PriceService priceService;
    @Mock Clock clock;
    @Mock ShoppingCartLogger logger;

    private ShoppingCartService shoppingCartService;

    @Before
    public void initialise() {
        shoppingCartService = new ShoppingCartService(
                clock,
                logger,
                priceService,
                shoppingCartRepository);
    }

    @Test public void
    add_items_to_shopping_cart() {
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(USER_ID, PRODUCT_ID, QUANTITY);
        given(priceService.priceFor(PRODUCT_ID)).willReturn(BigDecimal.valueOf(10.00));

        shoppingCartService.addProduct(USER_ID, PRODUCT_ID, QUANTITY);

        verify(shoppingCartRepository).add(shoppingCartItem);
    }

    @Test public void
    log_each_item_added_to_shopping_cart() {
        given(priceService.priceFor(PRODUCT_ID)).willReturn(BigDecimal.valueOf(10.00));
        given(clock.dateInYYYYMMDD()).willReturn("2016-07-01");

        shoppingCartService.addProduct(USER_ID, PRODUCT_ID, QUANTITY_10);

        verify(logger).log("[ITEM ADDED TO SHOPPING CART]: Date[2016-07-01], User[U1111], Product[P0012], Quantity[10], Total price[100.00]");
    }

}
