package com.codurance.craftingcode.exercise_10_shopping_cart;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class ShoppingBasketItem {
    private final ProductID productID;
    private final int quantity;

    public ShoppingBasketItem(ProductID productID, int quantity) {
        this.productID = productID;
        this.quantity = quantity;
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
