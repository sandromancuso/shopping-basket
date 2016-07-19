package com.codurance.shoppingbasket;

import java.math.BigDecimal;

public class ShoppingBasketService {

    private ProductService productService;
    private ShoppingBasketRepository shoppingBasketRepository;

	public ShoppingBasketService(ProductService productService,
	                             ShoppingBasketRepository shoppingBasketRepository) {
        this.productService = productService;
        this.shoppingBasketRepository = shoppingBasketRepository;
	}

    public void addItem(UserID userID, ProductID productID, int quantity) {
    	abortIfNotEnoughProductsInStock(productID, quantity);
        ShoppingBasket basket = shoppingBasketRepository.basketFor(userID);
        BigDecimal productUnitPrice = productService.priceFor(productID);
        basket.add(new ShoppingBasketItem(productID, quantity, productUnitPrice));
        shoppingBasketRepository.save(basket);
    }

	private void abortIfNotEnoughProductsInStock(ProductID productID, int quantity) {
		if (!productService.hasEnoughItemsInStock(productID, quantity)) {
			throw new NotEnoughItemsInStockException();
		}
	}

	public ShoppingBasket basketFor(UserID userID) {
        return shoppingBasketRepository.basketFor(userID);
    }

}
