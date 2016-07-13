package com.codurance.shoppingbasket;

import java.math.BigDecimal;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class ShoppingBasketItem {
    private final ProductID productID;
    private final int quantity;
    private BigDecimal unitPrice;

    public ShoppingBasketItem(ProductID productID, int quantity, BigDecimal unitPrice) {
        this.productID = productID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public BigDecimal totalPrice() {
        return BigDecimal.valueOf(quantity * unitPrice.doubleValue());
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
                "productID=" + productID +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
