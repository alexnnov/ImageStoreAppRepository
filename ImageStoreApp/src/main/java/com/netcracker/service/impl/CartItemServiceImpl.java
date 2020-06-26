package com.imagestore.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imagestore.domain.CartItem;
import com.imagestore.domain.Image;
import com.imagestore.domain.ImageToCartItem;
import com.imagestore.domain.ShoppingCart;
import com.imagestore.domain.User;
import com.imagestore.repository.CartItemRepository;
import com.imagestore.repository.ImageToCartItemRepository;
import com.imagestore.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService{
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private ImageToCartItemRepository imageToCartItemRepository;
	
	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
		return cartItemRepository.findByShoppingCart(shoppingCart);
	}
	
	public CartItem updateCartItem(CartItem cartItem) {
		BigDecimal bigDecimal = new BigDecimal(cartItem.getImage().getOurPrice()).multiply(new BigDecimal(cartItem.getQty()));
		
		bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		cartItem.setSubtotal(bigDecimal);
		
		cartItemRepository.save(cartItem);
		
		return cartItem;
	}

	
	public CartItem addImageToCartItem(Image image, User user, int qty) {
		List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());
		
		for (CartItem cartItem : cartItemList) {
			if(image.getId() == cartItem.getImage().getId()) {
				cartItem.setQty(cartItem.getQty()+qty);
				cartItem.setSubtotal(new BigDecimal(image.getOurPrice()).multiply(new BigDecimal(qty)));
				cartItemRepository.save(cartItem);
				return cartItem;
			}
		}
		
		CartItem cartItem = new CartItem();
		cartItem.setShoppingCart(user.getShoppingCart());
		cartItem.setImage(image);
		
		cartItem.setQty(qty);
		cartItem.setSubtotal(new BigDecimal(image.getOurPrice()).multiply(new BigDecimal(qty)));
		cartItem = cartItemRepository.save(cartItem);
		
		ImageToCartItem imageToCartItem = new ImageToCartItem();
		imageToCartItem.setImage(image);
		imageToCartItem.setCartItem(cartItem);
		imageToCartItemRepository.save(imageToCartItem);
		
		return cartItem;
	}
	
	public CartItem findById(Long id) {
		return cartItemRepository.findById(id).orElse(null);
	}
	
	public void removeCartItem(CartItem cartItem) {
		imageToCartItemRepository.deleteByCartItem(cartItem);
		cartItemRepository.delete(cartItem);
	}


}
