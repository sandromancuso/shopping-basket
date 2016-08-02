package com.codurance.shoppingbasket.basket;

import com.codurance.shoppingbasket.UserID;
import com.codurance.shoppingbasket.product.Product;
import com.codurance.shoppingbasket.product.ProductBuilder;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static java.math.BigDecimal.TEN;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingBasketRepositoryShould {

    private static final UserID USER_ID = new UserID("1234");
    private static final LocalDate CURRENT_DATE = LocalDate.now();
    private static final int QTY_2 = 2;
    private static final Product PRODUCT = ProductBuilder.aBook().withId("10001").costing(TEN).build();

    @Mock
    Clock clock;

    private ShoppingBasketRepository shoppingBasketRepository;

    @Before
    public void initialise() {
        given(clock.today()).willReturn(CURRENT_DATE);
        shoppingBasketRepository = new ShoppingBasketRepository(clock);
    }

    @Test public void
    create_and_return_a_new_shopping_basket_for_a_user_when_she_does_not_have_one() {
        ShoppingBasket shoppingBasket = shoppingBasketRepository.basketFor(USER_ID);

        assertThat(shoppingBasket, Is.is(ShoppingBasketBuilder.aShoppingBasket()
                                            .createdOn(CURRENT_DATE)
                                            .ownedBy(USER_ID)
                                            .build()));
    }

    @Test public void
    save_user_basket() {
        ShoppingBasket userBasket = ShoppingBasketBuilder.aShoppingBasket()
                                            .createdOn(CURRENT_DATE)
                                            .ownedBy(USER_ID)
                                            .withItem(PRODUCT, QTY_2)
                                            .build();
        shoppingBasketRepository.save(userBasket);

        ShoppingBasket shoppingBasket = shoppingBasketRepository.basketFor(USER_ID);

        assertThat(shoppingBasket, is(userBasket));
    }

}