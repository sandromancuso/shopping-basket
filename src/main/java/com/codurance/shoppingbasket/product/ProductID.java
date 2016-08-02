package com.codurance.shoppingbasket.product;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class ProductID {
    private String id;

    public ProductID(String id) {
        this.id = id;
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
        return "ProductID{" +
                "id='" + id + '\'' +
                '}';
    }
}
