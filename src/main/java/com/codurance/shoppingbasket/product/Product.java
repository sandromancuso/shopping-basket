package com.codurance.shoppingbasket.product;

import java.math.BigDecimal;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class Product {

	private final ProductID productID;
	private ProductType productType;
	private final BigDecimal unitPrice;
	private final int quantity;

	public Product(ProductID productID,
	               ProductType productType,
	               BigDecimal unitPrice,
	               int quantity) {
		this.productID = productID;
		this.productType = productType;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}

	public ProductID id() {
		return this.productID;
	}

	public int quantity() {
		return quantity;
	}

	public BigDecimal unitPrice() {
		return unitPrice;
	}

	public ProductType productType() {
		return productType;
	}

	@Override
	public boolean equals(Object o) {
		return reflectionEquals(this, o);
	}

	@Override
	public int hashCode() {
		return reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return "Product{" +
				"productID=" + productID +
				", productType=" + productType +
				", unitPrice=" + unitPrice +
				", quantity=" + quantity +
				'}';
	}
}
