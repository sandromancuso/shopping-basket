package com.codurance.craftingcode.exercise_10_shopping_cart;

public class ShoppingBasketService {

    private ShoppingBasketRepository shoppingBasketRepository;

    public ShoppingBasketService(ShoppingBasketRepository shoppingBasketRepository) {
        this.shoppingBasketRepository = shoppingBasketRepository;
    }

    public void addItem(UserID userID, ProductID productID, int quantity) {
        ShoppingBasketItem item = new ShoppingBasketItem(productID, quantity);
        shoppingBasketRepository.addItem(userID, item);
    }

    public ShoppingBasket basketFor(UserID userID) {
        return shoppingBasketRepository.basketFor(userID);
    }
}
