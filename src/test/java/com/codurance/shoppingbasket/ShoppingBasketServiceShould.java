package com.codurance.shoppingbasket;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static com.codurance.shoppingbasket.ShoppingBasketBuilder.aShoppingBasket;
import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingBasketServiceShould {

    private static final ProductID PRODUCT_ID_1 = new ProductID("10002");
	private static final ProductID PRODUCT_ID_2 = new ProductID("20001");
    private static final BigDecimal PRODUCT_1_UNIT_PRICE = BigDecimal.TEN;
    private static final BigDecimal PRODUCT_2_UNIT_PRICE = BigDecimal.TEN;
    private static final int QTY_2 = 2;
    private static final int QTY_5 = 5;


    private static final UserID USER_ID_1 = new UserID("1234");
	private static final UserID USER_ID_2 = new UserID("4321");

    private ShoppingBasket USER_1_SHOPPING_BASKET;
    private ShoppingBasket USER_2_SHOPPING_BASKET;


	@Mock ShoppingBasketRepository shoppingBasketRepository;
    @Mock PriceService priceService;
	@Mock ShoppingBasketLogger logger;

    private ShoppingBasketService shoppingBasketService;

	@Before
    public void initialise() {
        USER_1_SHOPPING_BASKET = aShoppingBasket().ownedBy(USER_ID_1).build();
		USER_2_SHOPPING_BASKET = aShoppingBasket().ownedBy(USER_ID_2).build();
		given(shoppingBasketRepository.basketFor(USER_ID_1)).willReturn(USER_1_SHOPPING_BASKET);
		given(shoppingBasketRepository.basketFor(USER_ID_2)).willReturn(USER_2_SHOPPING_BASKET);
        given(priceService.priceFor(PRODUCT_ID_1)).willReturn(PRODUCT_1_UNIT_PRICE);
        given(priceService.priceFor(PRODUCT_ID_2)).willReturn(PRODUCT_2_UNIT_PRICE);

        shoppingBasketService =
                new ShoppingBasketService(priceService, shoppingBasketRepository, logger);
    }

    @Test public void
    add_new_item_to_shopping_basket() {
        shoppingBasketService.addItem(USER_ID_1, PRODUCT_ID_1, QTY_2);

        verify(shoppingBasketRepository).save(
                aShoppingBasket().ownedBy(USER_ID_1).withItem(PRODUCT_ID_1, QTY_2, PRODUCT_1_UNIT_PRICE).build());
    }

    @Test public void
    return_a_shopping_basket_for_a_given_user() {
        ShoppingBasket shoppingBasket = shoppingBasketService.basketFor(USER_ID_1);

        assertThat(shoppingBasket, is(USER_1_SHOPPING_BASKET));
    }
    
	@Test public void
	log_items_added_to_shopping_basket() {
		shoppingBasketService.addItem(USER_ID_1, PRODUCT_ID_1, QTY_2);
		shoppingBasketService.addItem(USER_ID_2, PRODUCT_ID_2, QTY_5);

		verify(logger).log(format("[ITEM ADDED TO SHOPPING CART]: User[%s], Product[%s], Quantity[%d]",
				USER_ID_1.value(), PRODUCT_ID_1.value(), QTY_2));
		verify(logger).log(format("[ITEM ADDED TO SHOPPING CART]: User[%s], Product[%s], Quantity[%d]",
				USER_ID_2.value(), PRODUCT_ID_2.value(), QTY_5));
	}

}
