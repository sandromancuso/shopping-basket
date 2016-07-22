package com.codurance.shoppingbasket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBasketBuilder {

    private LocalDate creationDate = LocalDate.now();
    private UserID userID = new UserID("SOME ID");
    private List<ShoppingBasketItem> items = new ArrayList<>();
    private Discount discount = new NoDiscount();

    public static ShoppingBasketBuilder aShoppingBasket() {
        return new ShoppingBasketBuilder();
    }

    public ShoppingBasketBuilder createdOn(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public ShoppingBasketBuilder ownedBy(UserID userID) {
        this.userID = userID;
        return this;
    }

    public ShoppingBasketBuilder withItem(ProductID productID, int quantity, BigDecimal unitPrice) {
        this.items.add(new ShoppingBasketItem(productID, quantity, unitPrice));
        return this;
    }

	public ShoppingBasketBuilder with(Discount discount) {
		this.discount = discount;
		return this;
	}

    public ShoppingBasket build() {
        ShoppingBasket shoppingBasket = new ShoppingBasket(userID, creationDate);
        shoppingBasket.setDiscount(discount);
        items.forEach(shoppingBasket::add);
        return shoppingBasket;
    }
}
