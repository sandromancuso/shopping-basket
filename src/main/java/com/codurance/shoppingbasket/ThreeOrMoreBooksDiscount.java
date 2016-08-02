package com.codurance.shoppingbasket;

import static com.codurance.shoppingbasket.ProductType.BOOK;

public class ThreeOrMoreBooksDiscount implements Discount {

	@Override
	public int percentage() {
		return 10;
	}

	@Override
	public boolean isApplicableTo(ShoppingBasket basket) {
		return basket.numberOf(BOOK) >= 3;
	}
}
