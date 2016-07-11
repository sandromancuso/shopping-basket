package com.codurance.craftingcode.exercise_10_shopping_cart;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class ShoppingCartItem {

    private final UserId userId;
    private final ProductId productId;
    private final int quantity;

    public ShoppingCartItem(UserId userId, ProductId productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
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
