package com.codurance.shoppingbasket;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static com.codurance.shoppingbasket.ShoppingBasketBuilder.aShoppingBasket;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingBasketServiceShould {

    private static final ProductID PRODUCT_ID_1 = new ProductID("10002");
    private static final BigDecimal PRODUCT_1_UNIT_PRICE = BigDecimal.TEN;
    private static final int QTY_2 = 2;


    private static final UserID USER_ID_1 = new UserID("1234");

    private ShoppingBasket USER_1_SHOPPING_BASKET;


	@Mock ShoppingBasketRepository shoppingBasketRepository;
    @Mock PriceService priceService;
	@Mock StockService stockService;

    private ShoppingBasketService shoppingBasketService;

	@Before
    public void initialise() {
        USER_1_SHOPPING_BASKET = aShoppingBasket().ownedBy(USER_ID_1).build();
		given(shoppingBasketRepository.basketFor(USER_ID_1)).willReturn(USER_1_SHOPPING_BASKET);
        given(priceService.priceFor(PRODUCT_ID_1)).willReturn(PRODUCT_1_UNIT_PRICE);

        shoppingBasketService =
                new ShoppingBasketService(priceService, shoppingBasketRepository, stockService);
    }

    @Test public void
    add_new_item_to_shopping_basket() {
	    given(stockService.hasEnoughItemsInStock(PRODUCT_ID_1, QTY_2)).willReturn(true);

        shoppingBasketService.addItem(USER_ID_1, PRODUCT_ID_1, QTY_2);

        verify(shoppingBasketRepository).save(
                aShoppingBasket().ownedBy(USER_ID_1).withItem(PRODUCT_ID_1, QTY_2, PRODUCT_1_UNIT_PRICE).build());
    }

    @Test public void
    return_a_shopping_basket_for_a_given_user() {
        ShoppingBasket shoppingBasket = shoppingBasketService.basketFor(USER_ID_1);

        assertThat(shoppingBasket, is(USER_1_SHOPPING_BASKET));
    }

    @Test(expected = NotEnoughItemsInStockException.class) public void
    throw_exception_when_there_are_not_enough_items_in_stock() {
        given(stockService.hasEnoughItemsInStock(PRODUCT_ID_1, QTY_2)).willReturn(false);

	    shoppingBasketService.addItem(USER_ID_1, PRODUCT_ID_1, QTY_2);
    }
    
}
