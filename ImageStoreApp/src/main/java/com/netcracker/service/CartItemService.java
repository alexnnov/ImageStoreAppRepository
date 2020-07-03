package com.imagestore.service;

import java.util.List;

import com.imagestore.domain.CartItem;
import com.imagestore.domain.Image;
import com.imagestore.domain.Order;
import com.imagestore.domain.ShoppingCart;
import com.imagestore.domain.User;

public interface CartItemService {
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	CartItem updateCartItem(CartItem cartItem);
	
	CartItem addImageToCartItem(Image image, User user, int qty);
	
CartItem findById(Long id);
	
	void removeCartItem(CartItem cartItem);
	
	CartItem save(CartItem cartItem);
	
	List<CartItem> findByOrder(Order order);
}


