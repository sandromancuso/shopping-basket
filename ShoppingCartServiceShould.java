package com.codurance.craftingcode.exercise_10_shopping_cart;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartServiceShould {

    private static final UserId USER_ID = new UserId("some id");
    private static final ProductId PRODUCT_ID = new ProductId("product id");
    private static final int QUANTITY = 10;

    @Mock ShoppingCartRepository shoppingCartRepository;

    private ShoppingCartService shoppingCartService;

    @Before
    public void initialise() {
        shoppingCartService = new ShoppingCartService(shoppingCartRepository);
    }

    @Test public void
    add_items_to_shopping_cart() {
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(USER_ID, PRODUCT_ID, QUANTITY);

        shoppingCartService.addProduct(USER_ID, PRODUCT_ID, QUANTITY);

        verify(shoppingCartRepository).add(shoppingCartItem);
    }

}
