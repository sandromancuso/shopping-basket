package com.codurance.shoppingbasket;

import java.util.List;

public interface Discount {

	int percentage();

	boolean isApplicableTo(List<ShoppingBasketItem> shoppingBasketItems);
}
