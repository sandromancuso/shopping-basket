package com.codurance.shoppingbasket;

import org.junit.Before;
import org.junit.Test;

import static com.codurance.shoppingbasket.ProductService.THE_HOBBIT;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProductServiceShould {

	private ProductService productService;

	@Before
	public void initialise() {
		productService = new ProductService();
	}

	@Test public void
	inform_when_there_are_enough_items_in_stock() {
		assertThat(productService.hasEnoughItemsInStock(THE_HOBBIT.id(), 3), is(true));
		assertThat(productService.hasEnoughItemsInStock(THE_HOBBIT.id(), 15), is(false));
	}

}