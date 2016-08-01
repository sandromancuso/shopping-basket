package com.codurance.shoppingbasket;

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
    	abortIfNotEnoughProductsInStock(productID, quantity);
	    addProductToBasket(productID, quantity, basketFor(userID));
    }

	private void addProductToBasket(ProductID productID, int quantity, ShoppingBasket basket) {
		Product product = productService.productFor(productID);
		basket.add(new ShoppingBasketItem(product, quantity));
		basket.setDiscount(discountCalculator.discountFor(basket.items()));
		shoppingBasketRepository.save(basket);
	}

	public ShoppingBasket basketFor(UserID userID) {
		return shoppingBasketRepository.basketFor(userID);
	}

	private void abortIfNotEnoughProductsInStock(ProductID productID, int quantity) {
		if (!productService.hasEnoughItemsInStock(productID, quantity)) {
			throw new NotEnoughItemsInStockException();
		}
	}

}
