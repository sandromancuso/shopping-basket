package com.codurance.craftingcode.exercise_10_shopping_cart;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.codurance.craftingcode.exercise_10_shopping_cart.ShoppingBasketBuilder.aShoppingBasket;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingBasketServiceShould {

    private static final ProductID PRODUCT_ID = new ProductID("10002");
    private static final int QTY_2 = 2;
    private static final UserID USER_ID = new UserID("1234");
    private static final ShoppingBasket USER_SHOPPING_BASKET = aShoppingBasket().build();

    @Mock ShoppingBasketRepository shoppingBasketRepository;

    private ShoppingBasketService shoppingBasketService;

    @Before
    public void initialise() {
        shoppingBasketService = new ShoppingBasketService(shoppingBasketRepository);
    }

    @Test public void
    add_new_item_to_shopping_basket() {
        ShoppingBasketItem item = new ShoppingBasketItem(PRODUCT_ID, QTY_2);

        shoppingBasketService.addItem(USER_ID, PRODUCT_ID, QTY_2);

        verify(shoppingBasketRepository).addItem(USER_ID, item);
    }

    @Test public void
    return_a_shopping_basket_for_a_given_user() {
        given(shoppingBasketRepository.basketFor(USER_ID)).willReturn(USER_SHOPPING_BASKET);

        ShoppingBasket shoppingBasket = shoppingBasketService.basketFor(USER_ID);

        assertThat(shoppingBasket, is(USER_SHOPPING_BASKET));
    }

}
