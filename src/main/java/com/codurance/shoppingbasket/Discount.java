package com.codurance.shoppingbasket;

import java.util.List;

public interface Discount {

	int percentage();

	boolean isApplicableFor(List<ShoppingBasketItem> shoppingBasketItems);
}
