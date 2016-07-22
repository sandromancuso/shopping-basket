package com.codurance.shoppingbasket;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static com.codurance.shoppingbasket.ShoppingBasketBuilder.aShoppingBasket;
import static java.util.Collections.singletonList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingBasketServiceShould {

    private static final ProductID PRODUCT_ID_1 = new ProductID("10002");
    private static final BigDecimal PRODUCT_1_UNIT_PRICE = BigDecimal.TEN;
    private static final int QTY_2 = 2;
	private static final Discount NO_DISCOUNT = new NoDiscount();

    private static final UserID USER_ID_1 = new UserID("1234");
	private static final Discount TWENTY_PERCENT_DISCOUNT = new TwentyPercentDiscount(20);

	private ShoppingBasket USER_1_SHOPPING_BASKET;


	@Mock ShoppingBasketRepository shoppingBasketRepository;
    @Mock ProductService productService;
	@Mock DiscountCalculator discountCalculator;

    private ShoppingBasketService shoppingBasketService;

	@Before
    public void initialise() {
        USER_1_SHOPPING_BASKET = aShoppingBasket().ownedBy(USER_ID_1).build();
		given(shoppingBasketRepository.basketFor(USER_ID_1)).willReturn(USER_1_SHOPPING_BASKET);
        given(productService.priceFor(PRODUCT_ID_1)).willReturn(PRODUCT_1_UNIT_PRICE);

        shoppingBasketService =
                new ShoppingBasketService(productService, shoppingBasketRepository, discountCalculator);
    }

    @Test public void
    add_new_item_to_shopping_basket() {
	    ShoppingBasketItem shoppingBasketItem = new ShoppingBasketItem(PRODUCT_ID_1, QTY_2, PRODUCT_1_UNIT_PRICE);
	    given(discountCalculator.discountFor(singletonList(shoppingBasketItem)))
			    .willReturn(NO_DISCOUNT);
	    given(productService.hasEnoughItemsInStock(PRODUCT_ID_1, QTY_2)).willReturn(true);

        shoppingBasketService.addItem(USER_ID_1, PRODUCT_ID_1, QTY_2);

        verify(shoppingBasketRepository).save(
                aShoppingBasket().ownedBy(USER_ID_1)
		                         .withItem(PRODUCT_ID_1, QTY_2, PRODUCT_1_UNIT_PRICE)
		                         .with(NO_DISCOUNT)
		                         .build());
    }

    @Test public void
    return_a_shopping_basket_for_a_given_user() {
        ShoppingBasket shoppingBasket = shoppingBasketService.basketFor(USER_ID_1);

        assertThat(shoppingBasket, is(USER_1_SHOPPING_BASKET));
    }

    @Test(expected = NotEnoughItemsInStockException.class) public void
    throw_exception_when_there_are_not_enough_items_in_stock() {
        given(productService.hasEnoughItemsInStock(PRODUCT_ID_1, QTY_2)).willReturn(false);

	    shoppingBasketService.addItem(USER_ID_1, PRODUCT_ID_1, QTY_2);
    }

    @Test public void
    set_the_discount_after_adding_a_new_item() {
        given(productService.hasEnoughItemsInStock(PRODUCT_ID_1, QTY_2)).willReturn(true);
	    ShoppingBasketItem shoppingBasketItem = new ShoppingBasketItem(PRODUCT_ID_1, QTY_2, PRODUCT_1_UNIT_PRICE);
	    given(discountCalculator.discountFor(singletonList(shoppingBasketItem)))
			    .willReturn(TWENTY_PERCENT_DISCOUNT);

    	shoppingBasketService.addItem(USER_ID_1, PRODUCT_ID_1, QTY_2);

	    verify(shoppingBasketRepository).save(
			    aShoppingBasket().ownedBy(USER_ID_1)
					             .withItem(PRODUCT_ID_1, QTY_2, PRODUCT_1_UNIT_PRICE)
					             .with(TWENTY_PERCENT_DISCOUNT)
					             .build());
    }
    
}
