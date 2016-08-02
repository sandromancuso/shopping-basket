package com.codurance.shoppingbasket.basket;

import com.codurance.shoppingbasket.UserID;
import com.codurance.shoppingbasket.discount.Discount;
import com.codurance.shoppingbasket.discount.DiscountCalculator;
import com.codurance.shoppingbasket.product.Product;
import com.codurance.shoppingbasket.product.ProductID;
import com.codurance.shoppingbasket.product.ProductService;

public class ShoppingBasketService {

    private ProductService productService;
    private ShoppingBasketRepository shoppingBasketRepository;
	private DiscountCalculator discountCalculator;

	public ShoppingBasketService(ProductService productService,
	                             ShoppingBasketRepository shoppingBasketRepository,
	                             DiscountCalculator discountCalculator) {
        this.productService = productService;
        this.shoppingBasketRepository = shoppingBasketRepository;
		this.discountCalculator = discountCalculator;
	}

    public void addItem(UserID userID, ProductID productID, int quantity) {
    	abortIfNotEnoughItemsInStock(productID, quantity);
	    addProductToBasket(productID, quantity, basketFor(userID));
    }

	public ShoppingBasket basketFor(UserID userID) {
		return shoppingBasketRepository.basketFor(userID);
	}

	private void addProductToBasket(ProductID productID, int quantity, ShoppingBasket basket) {
		Product product = productService.productFor(productID);
		basket.add(new ShoppingBasketItem(product, quantity));
		basket.setDiscount(discountCalculator.discountFor(basket));
		shoppingBasketRepository.save(basket);
	}

	private void abortIfNotEnoughItemsInStock(ProductID productID, int quantity) {
		if (!productService.hasEnoughItemsInStock(productID, quantity)) {
			throw new NotEnoughItemsInStockException();
		}
	}

	public Discount basketDiscount(UserID userID) {
		ShoppingBasket shoppingBasket = shoppingBasketRepository.basketFor(userID);
		return discountCalculator.discountFor(shoppingBasket);
	}
}
