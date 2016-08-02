package com.codurance.shoppingbasket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class ShoppingBasket {
    private final UserID userID;
    private final LocalDate creationDate;
    private List<ShoppingBasketItem> items = new ArrayList<>();
	private Discount discount = new NoDiscount();

	ShoppingBasket(UserID userID, LocalDate creationDate) {
        this.userID = userID;
        this.creationDate = creationDate;
	}

    void add(ShoppingBasketItem item) {
        items.add(item);
    }

    public BigDecimal total() {
        return valueOf(items.stream()
                            .map(item -> item.totalPrice().doubleValue())
                            .reduce((t, acc) -> acc + t).orElse(0.0));
    }

    UserID userId() {
        return userID;
    }

	void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public boolean contains(ProductType productType) {
		return items.stream()
					.anyMatch(i -> i.productType() == productType);
	}

	public int numberOf(ProductType productType) {
		return items.stream()
					.filter(i -> i.productType() == productType)
					.map(i -> i.quantity())
					.reduce((quantity, acc) -> acc + quantity)
					.orElse(0);
	}

    @Override
    public boolean equals(Object o) {
        return reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

	@Override
	public String toString() {
		return "ShoppingBasket{" +
				"userID=" + userID +
				", creationDate=" + creationDate +
				", items=" + items +
				", discount=" + discount +
				'}';
	}
}
