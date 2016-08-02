package com.codurance.shoppingbasket.discount;

import com.codurance.shoppingbasket.ShoppingBasket;

import static com.codurance.shoppingbasket.ProductType.BOOK;
import static com.codurance.shoppingbasket.ProductType.VIDEO;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class AtLeastOneBookAndOneVideoDiscount implements Discount {
	private int percentage;

	public AtLeastOneBookAndOneVideoDiscount(int percentage) {
		this.percentage = percentage;
	}

	@Override
	public int percentage() {
		return percentage;
	}

	@Override
	public boolean isApplicableTo(ShoppingBasket basket) {
		return basket.contains(BOOK) && basket.contains(VIDEO);
	}

	@Override
	public boolean equals(Object o) {
		return reflectionEquals(this, o);
	}

	@Override
	public int hashCode() {
		return reflectionHashCode(this);
	}
}
