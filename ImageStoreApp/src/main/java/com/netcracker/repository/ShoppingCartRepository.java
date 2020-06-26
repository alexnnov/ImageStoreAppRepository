package com.imagestore.repository;

import org.springframework.data.repository.CrudRepository;

import com.imagestore.domain.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

}
