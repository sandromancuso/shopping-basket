package com.codurance.shoppingbasket.discount;

import com.codurance.shoppingbasket.basket.ShoppingBasket;

import static com.codurance.shoppingbasket.product.ProductType.BOOK;

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
