package com.codurance.shoppingbasket.discount;

import com.codurance.shoppingbasket.basket.ShoppingBasket;
import org.junit.Test;

import static com.codurance.shoppingbasket.basket.ShoppingBasketBuilder.aShoppingBasket;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DiscountCalculatorShould {

	private static final Discount FIVE_PERCENT_DISCOUNT = discountPercentageOf(5);
	private static final Discount TEN_PERCENT_DISCOUNT = discountPercentageOf(10);
	private static final Discount TWENTY_PERCENT_DISCOUNT = discountPercentageOf(20);

	@Test public void
	return_the_highest_discount_for_shopping_basket() {
		ShoppingBasket shoppingBasket = aShoppingBasket().build();
		DiscountCalculator discountCalculator = new DiscountCalculator(
				asList(TEN_PERCENT_DISCOUNT, TWENTY_PERCENT_DISCOUNT, FIVE_PERCENT_DISCOUNT));

		Discount discount = discountCalculator.discountFor(shoppingBasket);

		assertThat(discount, is(TWENTY_PERCENT_DISCOUNT));
	}

	private static Discount discountPercentageOf(int amount) {
		return new Discount() {
			@Override
			public int percentage() {
				return amount;
			}

			@Override
			public boolean isApplicableTo(ShoppingBasket shoppingBasket) {
				return true;
			}
		};
	}

}