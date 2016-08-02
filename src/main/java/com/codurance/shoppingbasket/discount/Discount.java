package com.codurance.shoppingbasket.discount;

import com.codurance.shoppingbasket.ShoppingBasket;

public interface Discount {

	int percentage();

	boolean isApplicableTo(ShoppingBasket basket);
}
