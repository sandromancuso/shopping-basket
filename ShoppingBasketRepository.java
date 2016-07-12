package com.codurance.craftingcode.exercise_10_shopping_cart;

import java.util.HashMap;
import java.util.Map;

public class ShoppingBasketRepository {

    private Clock clock;
    private Map<UserID, ShoppingBasket> baskets = new HashMap<>();

    public ShoppingBasketRepository(Clock clock) {
        this.clock = clock;
    }

    public void addItem(UserID userID, ShoppingBasketItem item) {
        ShoppingBasket basket = baskets.getOrDefault(userID, createBasket(userID));
        basket.add(item);
        baskets.put(userID, basket);
    }

    public ShoppingBasket basketFor(UserID userId) {
        return baskets.get(userId);
    }

    private ShoppingBasket createBasket(UserID userID) {
        return new ShoppingBasket(userID, clock.today());
    }
}
