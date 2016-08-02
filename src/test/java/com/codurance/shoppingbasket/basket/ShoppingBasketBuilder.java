package com.codurance.shoppingbasket.basket;

import com.codurance.shoppingbasket.UserID;
import com.codurance.shoppingbasket.product.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBasketBuilder {

    private LocalDate creationDate = LocalDate.now();
    private UserID userID = new UserID("SOME ID");
    private List<ShoppingBasketItem> items = new ArrayList<>();

    public static ShoppingBasketBuilder aShoppingBasket() {
        return new ShoppingBasketBuilder();
    }

    public ShoppingBasketBuilder createdOn(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public ShoppingBasketBuilder ownedBy(UserID userID) {
        this.userID = userID;
        return this;
    }

    public ShoppingBasketBuilder withItem(Product product, int quantity) {
        this.items.add(new ShoppingBasketItem(product, quantity));
        return this;
    }

    public ShoppingBasket build() {
        ShoppingBasket shoppingBasket = new ShoppingBasket(userID, creationDate);
        items.forEach(shoppingBasket::add);
        return shoppingBasket;
    }
}
