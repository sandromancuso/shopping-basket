package com.codurance.shoppingbasket;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ProductService {

    private static final BigDecimal TEN_POUNDS = BigDecimal.valueOf(10.0);
    private static final BigDecimal FIVE_POUNDS = BigDecimal.valueOf(5.0);
    private static final BigDecimal NINE_POUNDS = BigDecimal.valueOf(9.0);
    private static final BigDecimal SEVEN_POUNDS = BigDecimal.valueOf(7.0);

	private static final int QTY_10 = 10;

    public static final Product LOTR = new Product(new ProductID("10001"), TEN_POUNDS, QTY_10);
	public static final Product THE_HOBBIT = new Product(new ProductID("10002"), FIVE_POUNDS, QTY_10);
    public static final Product GAME_OF_THRONES = new Product(new ProductID("20001"), NINE_POUNDS, QTY_10);
    public static final Product BREAKING_BAD = new Product(new ProductID("20110"), SEVEN_POUNDS, QTY_10);


    private static Map<ProductID, Product> products = new HashMap<ProductID, Product>() {
        {
            put(LOTR.id(), LOTR);
            put(THE_HOBBIT.id(), THE_HOBBIT);
            put(GAME_OF_THRONES.id(), GAME_OF_THRONES);
            put(BREAKING_BAD.id(), BREAKING_BAD);
        }
    };

    public BigDecimal priceFor(ProductID productId) {
        return products.get(productId).price();
    }

    public boolean hasEnoughItemsInStock(ProductID productID, int quantity) {
	    return products.get(productID).quantity() >= quantity;
    }
}
