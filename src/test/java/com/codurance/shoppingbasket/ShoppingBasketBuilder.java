package com.codurance.shoppingbasket;

import com.codurance.shoppingbasket.discount.Discount;
import com.codurance.shoppingbasket.discount.NoDiscount;

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

    public ShoppingBasketBuilder withItem(Product product, int quantity) {
        this.items.add(new ShoppingBasketItem(product, quantity));
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
