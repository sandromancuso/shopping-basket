package com.codurance.shoppingbasket;

public interface Discount {

	int percentage();

	boolean isApplicableTo(ShoppingBasket basket);
}
