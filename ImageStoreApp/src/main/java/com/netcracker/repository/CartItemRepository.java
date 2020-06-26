package com.imagestore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.imagestore.domain.CartItem;
import com.imagestore.domain.ShoppingCart;


@Transactional
public interface CartItemRepository extends CrudRepository<CartItem, Long>{
	
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
}
