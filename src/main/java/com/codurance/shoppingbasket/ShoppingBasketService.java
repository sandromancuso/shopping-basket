package com.codurance.shoppingbasket;

import java.math.BigDecimal;

public class ShoppingBasketService {

    private ProductService productService;
    private ShoppingBasketRepository shoppingBasketRepository;
	private StockService stockService;

	public ShoppingBasketService(ProductService productService,
                                 ShoppingBasketRepository shoppingBasketRepository,
                                 StockService stockService) {
        this.productService = productService;
        this.shoppingBasketRepository = shoppingBasketRepository;
		this.stockService = stockService;
	}

    public void addItem(UserID userID, ProductID productID, int quantity) {
    	abortIfNotEnoughProductsInStock(productID, quantity);
        ShoppingBasket basket = shoppingBasketRepository.basketFor(userID);
        BigDecimal productUnitPrice = productService.priceFor(productID);
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
