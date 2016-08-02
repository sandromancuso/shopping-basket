package com.codurance.shoppingbasket.product;

import org.junit.Before;
import org.junit.Test;

import static com.codurance.shoppingbasket.product.ProductService.*;
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

	@Test public void
	return_a_product_by_its_ID() {
	    assertThat(productService.productFor(LOTR.id()), is(LOTR));
	    assertThat(productService.productFor(THE_HOBBIT.id()), is(THE_HOBBIT));
	    assertThat(productService.productFor(GAME_OF_THRONES.id()), is(GAME_OF_THRONES));
	    assertThat(productService.productFor(BREAKING_BAD.id()), is(BREAKING_BAD));
	}

}