package com.codurance.shoppingbasket;

import java.math.BigDecimal;

public class ShoppingBasketService {

    private PriceService priceService;
    private ShoppingBasketRepository shoppingBasketRepository;

    public ShoppingBasketService(PriceService priceService,
                                 ShoppingBasketRepository shoppingBasketRepository) {
        this.priceService = priceService;
        this.shoppingBasketRepository = shoppingBasketRepository;
    }

    public void addItem(UserID userID, ProductID productID, int quantity) {
        ShoppingBasket basket = shoppingBasketRepository.basketFor(userID);
        BigDecimal productUnitPrice = priceService.priceFor(productID);
        basket.add(new ShoppingBasketItem(productID, quantity, productUnitPrice));
        shoppingBasketRepository.save(basket);
    }

	public ShoppingBasket basketFor(UserID userID) {
        return shoppingBasketRepository.basketFor(userID);
    }

}
