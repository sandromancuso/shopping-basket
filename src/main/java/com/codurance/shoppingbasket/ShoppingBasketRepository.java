package com.codurance.shoppingbasket;

import java.util.HashMap;
import java.util.Map;

public class ShoppingBasketRepository {

    private Clock clock;
    private Map<UserID, ShoppingBasket> baskets = new HashMap<>();

    public ShoppingBasketRepository(Clock clock) {
        this.clock = clock;
    }

    ShoppingBasket basketFor(UserID userId) {
        return baskets.getOrDefault(userId, createBasket(userId));
    }

    void save(ShoppingBasket basket) {
        baskets.put(basket.userId(), basket);
    }

    private ShoppingBasket createBasket(UserID userID) {
        return new ShoppingBasket(userID, clock.today());
    }
}
