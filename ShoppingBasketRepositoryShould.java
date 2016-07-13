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
public class ShoppingBasketRepositoryShould {

    private static final UserID USER_ID = new UserID("1234");
    private static final LocalDate CURRENT_DATE = LocalDate.now();
    private static final ProductID PRODUCT_ID = new ProductID("10001");
    private static final int QTY_2 = 2;
    private static final BigDecimal PRODUCT_UNIT_PRICE = BigDecimal.TEN;

    @Mock Clock clock;

    private ShoppingBasketRepository shoppingBasketRepository;

    @Before
    public void initialise() {
        given(clock.today()).willReturn(CURRENT_DATE);
        shoppingBasketRepository = new ShoppingBasketRepository(clock);
    }

    @Test public void
    create_and_return_a_new_shopping_basket_for_a_user_when_she_does_not_have_one() {
        ShoppingBasket shoppingBasket = shoppingBasketRepository.basketFor(USER_ID);

        assertThat(shoppingBasket, is(aShoppingBasket()
                                            .createdOn(CURRENT_DATE)
                                            .ownedBy(USER_ID)
                                            .build()));
    }

    @Test public void
    save_user_basket() {
        ShoppingBasket userBasket = aShoppingBasket()
                                            .createdOn(CURRENT_DATE)
                                            .ownedBy(USER_ID)
                                            .withItem(PRODUCT_ID, QTY_2, PRODUCT_UNIT_PRICE)
                                            .build();

        ShoppingBasket shoppingBasket = shoppingBasketRepository.basketFor(USER_ID);

        assertThat(shoppingBasket, is(userBasket));
    }

}