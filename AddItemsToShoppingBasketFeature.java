package com.codurance.craftingcode.exercise_10_shopping_cart;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.codurance.craftingcode.exercise_10_shopping_cart.ShoppingBasketBuilder.aShoppingBasket;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class AddItemsToShoppingBasketFeature {

    private static final UserID USER_ID_1 = new UserID("1234");
    private static final LocalDate CREATION_DATE = LocalDate.of(2016, 7, 1);

    private static final ProductID THE_HOBBIT_ID = new ProductID("10002");
    private static final BigDecimal UNIT_PRICE_FOR_THE_HOBBIT = BigDecimal.valueOf(5);
    private static final int QTY_2 = 2;

    private static final ProductID BREAKING_BAD_ID = new ProductID("20110");
    private static final BigDecimal UNIT_PRICE_FOR_BREAKING_BAD = BigDecimal.valueOf(7);
    private static final int QTY_5 = 5;

    @Mock Clock clock;

    private ShoppingBasketService shoppingBasketService;

    @Before
    public void initialise() {
        ShoppingBasketRepository shoppingBasketRepository = new ShoppingBasketRepository(clock);
        PriceService priceService = new PriceService();
        shoppingBasketService = new ShoppingBasketService(priceService, shoppingBasketRepository);
    }

    @Test public void
    display_shopping_basket_after_adding_items_to_it() {
        given(clock.today()).willReturn(CREATION_DATE);
        shoppingBasketService.addItem(USER_ID_1, THE_HOBBIT_ID, QTY_2);
        shoppingBasketService.addItem(USER_ID_1, BREAKING_BAD_ID, QTY_5);

        ShoppingBasket shoppingBasket = shoppingBasketService.basketFor(USER_ID_1);

        assertThat(shoppingBasket, is(aShoppingBasket()
                                            .createdOn(CREATION_DATE)
                                            .ownedBy(USER_ID_1)
                                            .withItem(THE_HOBBIT_ID, QTY_2, null)
                                            .withItem(BREAKING_BAD_ID, QTY_5, null)
                                            .build()));
        assertThat(shoppingBasket.total(), is(BigDecimal.valueOf(45)));
    }

}
