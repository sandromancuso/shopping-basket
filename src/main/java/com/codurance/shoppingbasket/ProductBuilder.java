package com.codurance.shoppingbasket;

import java.math.BigDecimal;

import static com.codurance.shoppingbasket.ProductType.BOOK;
import static com.codurance.shoppingbasket.ProductType.VIDEO;

public class ProductBuilder {

	private ProductType productType = BOOK;
	private ProductID productID = new ProductID("1234");
	private BigDecimal price = BigDecimal.TEN;
	private int quantity = 0;

	private ProductBuilder(ProductType productType) {
		this.productType = productType;
	}

	public static ProductBuilder aBook() {
        return new ProductBuilder(BOOK);
    }

	public static ProductBuilder aVideo() {
		return new ProductBuilder(VIDEO);
	}

    public ProductBuilder withId(String value) {
        this.productID = new ProductID(value);
        return this;
    }

    public ProductBuilder costing(BigDecimal price) {
        this.price = price;
	    return this;
    }

	public ProductBuilder withQuantity(int quantity) {
		this.quantity = quantity;
		return this;
	}

	public Product build() {
		return new Product(productID, productType, price, quantity);
	}
}
