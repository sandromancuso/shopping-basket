package com.codurance.craftingcode.exercise_10_shopping_cart;

import com.codurance.craftingcode.exercise_05_vanila_bank_kata.Clock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddItemToShoppingCartFeature {

    private static final UserId USER_01 = new UserId("U0001");
    private static final UserId USER_02 = new UserId("U0002");
    private static final ProductId BREAKING_BAD_S1 = new ProductId("20110");
    private static final ProductId THE_HOBBIT = new ProductId("10002");

    @Mock ShoppingCartLogger logger;
    @Mock Clock clock;

    private ShoppingCartService shoppingCartService;

    @Before
    public void initialise() {
        ProductService productService = new ProductService();
        ShoppingCartRepository shoppingCartRepository = new ShoppingCartRepository();
        shoppingCartService = new ShoppingCartService(clock, logger, productService, shoppingCartRepository);
    }

    @Test public void
    add_item_to_shopping_cart() {
        given(clock.dateInYYYYMMDD()).willReturn("2016-07-01", "2016-07-02");

        shoppingCartService.addProduct(USER_01, BREAKING_BAD_S1, 2);
        shoppingCartService.addProduct(USER_02, THE_HOBBIT, 5);

        verify(logger).log("[ITEM ADDED TO SHOPPING CART]: Date[2016-07-01], User[U0001], Product[20110], Quantity[2], Total price[14.00]");
        verify(logger).log("[ITEM ADDED TO SHOPPING CART]: Date[2016-07-02], User[U0002], Product[10002], Quantity[5], Total price[25.00]");
    }
}
