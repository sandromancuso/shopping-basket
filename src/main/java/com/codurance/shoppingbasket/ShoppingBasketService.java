package com.codurance.shoppingbasket;

import java.math.BigDecimal;

public class ShoppingBasketService {

    private PriceService priceService;
    private ShoppingBasketRepository shoppingBasketRepository;
	private StockService stockService;

	public ShoppingBasketService(PriceService priceService,
                                 ShoppingBasketRepository shoppingBasketRepository,
                                 StockService stockService) {
        this.priceService = priceService;
        this.shoppingBasketRepository = shoppingBasketRepository;
		this.stockService = stockService;
	}

    public void addItem(UserID userID, ProductID productID, int quantity) {
    	abortIfNotEnoughProductsInStock(productID, quantity);
        ShoppingBasket basket = shoppingBasketRepository.basketFor(userID);
        BigDecimal productUnitPrice = priceService.priceFor(productID);
        basket.add(new ShoppingBasketItem(productID, quantity, productUnitPrice));
        shoppingBasketRepository.save(basket);
    }

	private void abortIfNotEnoughProductsInStock(ProductID productID, int quantity) {
		if (!stockService.hasEnoughItemsInStock(productID, quantity)) {
			throw new NotEnoughItemsInStockException();
		}
	}

	public ShoppingBasket basketFor(UserID userID) {
        return shoppingBasketRepository.basketFor(userID);
    }

}
