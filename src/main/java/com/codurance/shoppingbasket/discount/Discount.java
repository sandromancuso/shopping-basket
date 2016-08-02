package com.codurance.shoppingbasket.discount;

import com.codurance.shoppingbasket.basket.ShoppingBasket;

public interface Discount {

	int percentage();

	boolean isApplicableTo(ShoppingBasket basket);
}
