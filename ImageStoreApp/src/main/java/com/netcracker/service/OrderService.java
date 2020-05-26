package com.netcracker.service;

import com.netcracker.entity.Order;
import com.netcracker.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void add(Order order){
        orderRepository.save(order);
    }

    public void update(Order order){
        orderRepository.save(order);
    }

    public void remove(int id) {
        orderRepository.deleteById(id);
    }

    public Order find(int id){
        Optional<Order> optional = orderRepository.findById(id);
        return optional.orElse(null);
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }
}
