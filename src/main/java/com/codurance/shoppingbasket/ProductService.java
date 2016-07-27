package com.codurance.shoppingbasket;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.codurance.shoppingbasket.ProductBuilder.aBook;
import static com.codurance.shoppingbasket.ProductBuilder.aVideo;

public class ProductService {

    private static final BigDecimal TEN_POUNDS = BigDecimal.valueOf(10.0);
    private static final BigDecimal FIVE_POUNDS = BigDecimal.valueOf(5.0);
    private static final BigDecimal NINE_POUNDS = BigDecimal.valueOf(9.0);
    private static final BigDecimal SEVEN_POUNDS = BigDecimal.valueOf(7.0);

	private static final int QTY_10 = 10;

	static final Product LOTR = aBook().withId("10001").costing(TEN_POUNDS).withQuantity(QTY_10).build();
	static final Product THE_HOBBIT = aBook().withId("10002").costing(FIVE_POUNDS).withQuantity(QTY_10).build();
	static final Product GAME_OF_THRONES = aVideo().withId("20001").costing(NINE_POUNDS).withQuantity(QTY_10).build();
	static final Product BREAKING_BAD = aVideo().withId("20110").costing(SEVEN_POUNDS).withQuantity(QTY_10).build();


	private static Map<ProductID, Product> products = new HashMap<ProductID, Product>() {
        {
            put(LOTR.id(), LOTR);
            put(THE_HOBBIT.id(), THE_HOBBIT);
            put(GAME_OF_THRONES.id(), GAME_OF_THRONES);
            put(BREAKING_BAD.id(), BREAKING_BAD);
        }
    };

    BigDecimal priceFor(ProductID productId) {
        return products.get(productId).price();
    }

    boolean hasEnoughItemsInStock(ProductID productID, int quantity) {
	    return products.get(productID).quantity() >= quantity;
    }
}
