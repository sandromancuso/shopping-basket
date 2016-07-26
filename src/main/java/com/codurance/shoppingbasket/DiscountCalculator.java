package com.codurance.shoppingbasket;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;

public class DiscountCalculator {

	private List<Discount> discounts = EMPTY_LIST;

	public DiscountCalculator(List<Discount> discounts) {
		this.discounts = discounts;
	}

	Discount discountFor(List<ShoppingBasketItem> shoppingBasketItems) {
		return discounts.stream()
				.filter(discount -> discount.isApplicableTo(shoppingBasketItems))
				.max((d1, d2) -> (d1.percentage() > d2.percentage()) ? +1 : -1)
				.orElse(new NoDiscount());
	}

}
