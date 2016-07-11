package com.codurance.craftingcode.exercise_10_shopping_cart;

public class ShoppingCartService {
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public void addProduct(UserId userId, ProductId productId, int quantity) {
        ShoppingCartItem item = new ShoppingCartItem(userId, productId, quantity);
        shoppingCartRepository.add(item);
    }
}
