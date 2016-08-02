package com.codurance.shoppingbasket.discount;

import com.codurance.shoppingbasket.ShoppingBasket;
import com.codurance.shoppingbasket.discount.Discount;
import com.codurance.shoppingbasket.discount.NoDiscount;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;

public class DiscountCalculator {

	private List<Discount> discounts = EMPTY_LIST;

	public DiscountCalculator(List<Discount> discounts) {
		this.discounts = discounts;
	}

	public Discount discountFor(ShoppingBasket shoppingBasket) {
		return discounts.stream()
				.filter(discount -> discount.isApplicableTo(shoppingBasket))
				.max((d1, d2) -> (d1.percentage() > d2.percentage()) ? +1 : -1)
				.orElse(new NoDiscount());
	}

}
