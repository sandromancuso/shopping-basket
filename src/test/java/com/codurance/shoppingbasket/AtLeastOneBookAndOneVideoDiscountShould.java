package com.codurance.shoppingbasket;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AtLeastOneBookAndOneVideoDiscountShould {

	@Test public void
	return_its_percentage() {
		AtLeastOneBookAndOneVideoDiscount discount = new AtLeastOneBookAndOneVideoDiscount(20);

		assertThat(discount.percentage(), is(20));
	}

}