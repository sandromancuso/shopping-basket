package com.codurance.shoppingbasket;

import java.math.BigDecimal;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class ShoppingBasketItem {
    private final int quantity;
    private final Product product;

    public ShoppingBasketItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public BigDecimal totalPrice() {
        return BigDecimal.valueOf(quantity * product.unitPrice().doubleValue());
    }

    public ProductType productType() {
        return this.product.productType();
    }

    public int quantity() {
        return quantity;
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
        return "ShoppingBasketItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
