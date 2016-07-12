package com.codurance.craftingcode.exercise_10_shopping_cart;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {
    private final UserID userID;
    private final LocalDate creationDate;
    private List<ShoppingBasketItem> items = new ArrayList<>();

    public ShoppingBasket(UserID userID, LocalDate creationDate) {
        this.userID = userID;
        this.creationDate = creationDate;
    }

    public void add(ShoppingBasketItem item) {
        items.add(item);
    }

    public BigDecimal total() {
        throw new UnsupportedOperationException();
    }
}
