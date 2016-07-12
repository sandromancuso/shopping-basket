package com.codurance.craftingcode.exercise_10_shopping_cart;

import java.math.BigDecimal;

public class ShoppingBasketItem {
    private final ProductID productID;
    private final int quantity;
    private final BigDecimal unitPrice;

    public ShoppingBasketItem(ProductID productID, int quantity, BigDecimal unitPrice) {
        this.productID = productID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
