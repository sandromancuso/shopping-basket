package com.codurance.shoppingbasket;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

class TwentyPercentDiscount implements Discount {
	private int percentage;

	TwentyPercentDiscount(int percentage) {
		this.percentage = percentage;
	}

	@Override
	public int percentage() {
		return percentage;
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
