package com.codurance.craftingcode.exercise_10_shopping_cart;

public class ShoppingBasketService {

    private UserID userID;
    private ProductID productID;
    private int quantity;

    public void addItem(UserID userID, ProductID productID, int quantity) {
        this.userID = userID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public ShoppingBasket basketFor(UserID userID) {
        return null;
    }
}
