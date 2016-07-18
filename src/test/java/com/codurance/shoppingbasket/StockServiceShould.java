package com.codurance.shoppingbasket;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StockServiceShould {

	private static final ProductID PRODUCT_ID = new ProductID("10001");
	private static final int QTY_4 = 4;

	private StockService stockService;

	@Before
	public void initialise() {
		stockService = new StockService();
	}

	@Test public void
	inform_when_there_are_not_enough_items_in_stock() {
	    assertThat(stockService.hasEnoughItemsInStock(PRODUCT_ID, QTY_4), is(false));
	}

}