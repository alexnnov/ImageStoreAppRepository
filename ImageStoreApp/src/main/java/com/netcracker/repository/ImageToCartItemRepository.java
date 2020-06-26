package com.imagestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.imagestore.domain.CartItem;
import com.imagestore.domain.ImageToCartItem;


@Transactional
public interface ImageToCartItemRepository extends CrudRepository<ImageToCartItem, Long> {
	
	void deleteByCartItem(CartItem cartItem);

}
