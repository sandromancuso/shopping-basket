package com.codurance.shoppingbasket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class ShoppingBasket {
    private final UserID userID;
    private final LocalDate creationDate;
    private List<ShoppingBasketItem> items = new ArrayList<>();

    ShoppingBasket(UserID userID, LocalDate creationDate) {
        this.userID = userID;
        this.creationDate = creationDate;
    }

    public void add(ShoppingBasketItem item) {
        items.add(item);
    }

    public BigDecimal total() {
        return BigDecimal.valueOf(items.stream()
                     .map(item -> item.totalPrice().doubleValue())
                     .reduce((t, acc) -> acc + t).orElse(0.0));
    }

    UserID userId() {
        return userID;
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
        return "ShoppingBasket{" +
                "creationDate=" + creationDate +
                ", userID=" + userID +
                ", items=" + items +
                '}';
    }
}
