package com.imagestore.service;

import com.imagestore.domain.ShoppingCart;

public interface ShoppingCartService {
	ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);
	void clearShoppingCart(ShoppingCart shoppingCart);
}
