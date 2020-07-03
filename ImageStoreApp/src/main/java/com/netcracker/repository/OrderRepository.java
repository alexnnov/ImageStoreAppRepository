package com.imagestore.repository;

import org.springframework.data.repository.CrudRepository;

import com.imagestore.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
