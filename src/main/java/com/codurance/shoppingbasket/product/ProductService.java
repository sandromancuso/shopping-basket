package com.codurance.shoppingbasket.product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ProductService {

    private static final BigDecimal TEN_POUNDS = BigDecimal.valueOf(10.0);
    private static final BigDecimal FIVE_POUNDS = BigDecimal.valueOf(5.0);
    private static final BigDecimal NINE_POUNDS = BigDecimal.valueOf(9.0);
    private static final BigDecimal SEVEN_POUNDS = BigDecimal.valueOf(7.0);

	private static final int QTY_10 = 10;

	public static final Product LOTR = ProductBuilder.aBook().withId("10001").costing(TEN_POUNDS).withQuantity(QTY_10).build();
	public static final Product THE_HOBBIT = ProductBuilder.aBook().withId("10002").costing(FIVE_POUNDS).withQuantity(QTY_10).build();
	public static final Product GAME_OF_THRONES = ProductBuilder.aVideo().withId("20001").costing(NINE_POUNDS).withQuantity(QTY_10).build();
	public static final Product BREAKING_BAD = ProductBuilder.aVideo().withId("20110").costing(SEVEN_POUNDS).withQuantity(QTY_10).build();


	private static Map<ProductID, Product> products = new HashMap<ProductID, Product>() {
        {
            put(LOTR.id(), LOTR);
            put(THE_HOBBIT.id(), THE_HOBBIT);
            put(GAME_OF_THRONES.id(), GAME_OF_THRONES);
            put(BREAKING_BAD.id(), BREAKING_BAD);
        }
    };

	public Product productFor(ProductID productID) {
		return products.get(productID);
	}

    public boolean hasEnoughItemsInStock(ProductID productID, int quantity) {
	    return products.get(productID).quantity() >= quantity;
    }
}
