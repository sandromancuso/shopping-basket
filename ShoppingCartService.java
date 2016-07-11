package com.codurance.craftingcode.exercise_10_shopping_cart;

import com.codurance.craftingcode.exercise_05_vanila_bank_kata.Clock;

import java.text.DecimalFormat;

public class ShoppingCartService {
    private final Clock clock;
    private final ShoppingCartLogger logger;
    private ProductService productService;
    private ShoppingCartRepository shoppingCartRepository;

    private static final DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public ShoppingCartService(Clock clock,
                               ShoppingCartLogger logger,
                               ProductService productService,
                               ShoppingCartRepository shoppingCartRepository) {
        this.clock = clock;
        this.logger = logger;
        this.productService = productService;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public void addProduct(UserId userId, ProductId productId, int quantity) {
        ShoppingCartItem item = new ShoppingCartItem(userId, productId, quantity);
        shoppingCartRepository.add(item);
        logger.log(itemAddedToShoppingCartMessage(item));
    }

    private String itemAddedToShoppingCartMessage(ShoppingCartItem item) {
        String totalPrice =  decimalFormat.format(item.quantity() * productService.priceFor(item.productId()).intValue());
        return "[ITEM ADDED TO SHOPPING CART]: "
                + "Date[" + clock.dateInYYYYMMDD() + "], "
                + "User[" + item.userId().id() + "], "
                + "Product[" + item.productId().id() + "], "
                + "Quantity[" + item.quantity() + "], "
                + "Total price[" + totalPrice + "]";
    }
}
