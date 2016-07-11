package com.codurance.craftingcode.exercise_10_shopping_cart;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class ProductId {
    private String id;

    public ProductId(String id) {
        this.id = id;
    }

    public String id() {
        return this.id;
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
