package com.codurance.craftingcode.exercise_10_shopping_cart;

import java.math.BigDecimal;

public class ShoppingBasketService {

    private PriceService priceService;
    private ShoppingBasketRepository shoppingBasketRepository;

    public ShoppingBasketService(PriceService priceService, ShoppingBasketRepository shoppingBasketRepository) {
        this.priceService = priceService;
        this.shoppingBasketRepository = shoppingBasketRepository;
    }

    public void addItem(UserID userID, ProductID productID, int quantity) {
        BigDecimal productUnitPrice = priceService.priceFor(productID);
        ShoppingBasketItem item = new ShoppingBasketItem(productID, quantity, productUnitPrice);
        shoppingBasketRepository.addItem(userID, item);
    }

    public ShoppingBasket basketFor(UserID userID) {
        return shoppingBasketRepository.basketFor(userID);
    }
}
