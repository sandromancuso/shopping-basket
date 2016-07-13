package com.codurance.craftingcode.exercise_10_shopping_cart;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

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
    private static final BigDecimal PRODUCT_UNIT_PRICE = BigDecimal.TEN;

    @Mock ShoppingBasketRepository shoppingBasketRepository;
    @Mock PriceService priceService;

    private ShoppingBasketService shoppingBasketService;

    @Before
    public void initialise() {
        shoppingBasketService =
                new ShoppingBasketService(priceService, shoppingBasketRepository);
    }

    @Test public void
    add_new_item_to_shopping_basket() {
        ShoppingBasket shoppingBasket = aShoppingBasket().build();
        given(shoppingBasketRepository.basketFor(USER_ID)).willReturn(shoppingBasket);
        given(priceService.priceFor(PRODUCT_ID)).willReturn(PRODUCT_UNIT_PRICE);

        shoppingBasketService.addItem(USER_ID, PRODUCT_ID, QTY_2);

        verify(shoppingBasketRepository).save(
                aShoppingBasket().withItem(PRODUCT_ID, QTY_2, PRODUCT_UNIT_PRICE).build());
    }

    @Test public void
    return_a_shopping_basket_for_a_given_user() {
        given(shoppingBasketRepository.basketFor(USER_ID))
                .willReturn(USER_SHOPPING_BASKET);

        ShoppingBasket shoppingBasket = shoppingBasketService.basketFor(USER_ID);

        assertThat(shoppingBasket, is(USER_SHOPPING_BASKET));
    }

}
