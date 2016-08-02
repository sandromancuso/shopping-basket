package com.codurance.shoppingbasket;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class NoDiscount implements Discount {

	private int percentage = 0;

	@Override
	public int percentage() {
		return percentage;
	}

	@Override
	public boolean isApplicableTo(ShoppingBasket basket) {
		return true;
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
