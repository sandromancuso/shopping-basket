package com.codurance.shoppingbasket;

import java.math.BigDecimal;

import static java.lang.String.format;

public class ShoppingBasketService {

    private PriceService priceService;
    private ShoppingBasketRepository shoppingBasketRepository;
    private ShoppingBasketLogger logger;

    public ShoppingBasketService(PriceService priceService,
                                 ShoppingBasketRepository shoppingBasketRepository,
                                 ShoppingBasketLogger logger) {
        this.priceService = priceService;
        this.shoppingBasketRepository = shoppingBasketRepository;
        this.logger = logger;
    }

    public void addItem(UserID userID, ProductID productID, int quantity) {
        ShoppingBasket basket = shoppingBasketRepository.basketFor(userID);
        BigDecimal productUnitPrice = priceService.priceFor(productID);
        basket.add(new ShoppingBasketItem(productID, quantity, productUnitPrice));
        shoppingBasketRepository.save(basket);
	    logItemAdded(userID, productID, quantity);
    }

	public ShoppingBasket basketFor(UserID userID) {
        return shoppingBasketRepository.basketFor(userID);
    }

	private void logItemAdded(UserID userID, ProductID productID, int quantity) {
		logger.log(format("[ITEM ADDED TO SHOPPING CART]: User[%s], Product[%s], Quantity[%d]",
				userID.value(), productID.value(), quantity));
	}
}
