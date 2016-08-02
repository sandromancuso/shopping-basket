package com.codurance.shoppingbasket.discount;

import com.codurance.shoppingbasket.basket.ShoppingBasket;
import org.junit.Before;
import org.junit.Test;

import static com.codurance.shoppingbasket.basket.ShoppingBasketBuilder.aShoppingBasket;
import static com.codurance.shoppingbasket.product.ProductBuilder.aBook;
import static com.codurance.shoppingbasket.product.ProductBuilder.aVideo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ThreeOrMoreBooksDiscountShould {

	private static final int QTY_2 = 2;
	private static final int QTY_3 = 3;
	private Discount discount;

	@Before
	public void initialise() {
	    discount = new ThreeOrMoreBooksDiscount();
	}

	@Test public void
	return_a_10_percent_discount() {
	    assertThat(discount.percentage(), is(10));
	}

	@Test public void
	not_be_applicable_when_basket_has_less_than_three_books() {
		ShoppingBasket emptyBasket = aShoppingBasket().build();
		ShoppingBasket basketWithTwoBooks = aShoppingBasket()
												.withItem(aBook().build(), QTY_2)
												.withItem(aVideo().build(), QTY_2)
												.build();

		assertThat(discount.isApplicableTo(emptyBasket), is(false));
		assertThat(discount.isApplicableTo(basketWithTwoBooks), is(false));
	}

	@Test public void
	be_applicable_for_baskets_with_three_items() {
		ShoppingBasket basket = aShoppingBasket()
											.withItem(aBook().build(), QTY_3)
											.build();

		assertThat(discount.isApplicableTo(basket), is(true));
	}

	@Test public void
	be_applicable_for_baskets_with_more_than_three_items() {
		ShoppingBasket basket = aShoppingBasket()
											.withItem(aBook().build(), QTY_2)
											.withItem(aBook().build(), QTY_2)
											.build();

		assertThat(discount.isApplicableTo(basket), is(true));
	}
}
