package com.codurance.shoppingbasket.discount;

import com.codurance.shoppingbasket.basket.ShoppingBasket;
import org.junit.Before;
import org.junit.Test;

import static com.codurance.shoppingbasket.basket.ShoppingBasketBuilder.aShoppingBasket;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NoDiscountShould {

	private Discount discount;

	@Before
	public void initialise() {
	    discount = new NoDiscount();
	}

	@Test public void
	return_percentage_of_zero() {
	    assertThat(discount.percentage(), is(0));
	}

	@Test public void
	be_applicable_to_any_shopping_basket() {
		ShoppingBasket shoppingBasket = aShoppingBasket().build();

		assertThat(discount.isApplicableTo(shoppingBasket), is(true));
	}

}