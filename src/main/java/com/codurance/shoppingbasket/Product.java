package com.codurance.shoppingbasket;

import java.math.BigDecimal;

public class Product {

	private final ProductID productID;
	private final BigDecimal price;
	private final int quantity;

	public Product(ProductID productID, BigDecimal price, int quantity) {
		this.productID = productID;
		this.price = price;
		this.quantity = quantity;
	}

	public ProductID id() {
		return this.productID;
	}

	public BigDecimal price() {
		return price;
	}

	public int quantity() {
		return quantity;
	}
}
