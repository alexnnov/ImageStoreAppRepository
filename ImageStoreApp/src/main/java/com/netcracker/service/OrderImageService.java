package com.netcracker.service;

import com.netcracker.entity.OrderImage;
import com.netcracker.repository.OrderImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderImageService {
    @Autowired
    private OrderImageRepository orderOrderImageRepository;

    public void add(OrderImage orderOrderImage){
        orderOrderImageRepository.save(orderOrderImage);
    }

    public void update(OrderImage orderOrderImage){
        orderOrderImageRepository.save(orderOrderImage);
    }

    public void remove(int id) {
        orderOrderImageRepository.deleteById(id);
    }

    public OrderImage find(int id){
        Optional<OrderImage> optional = orderOrderImageRepository.findById(id);
        return optional.orElse(null);
    }

    public List<OrderImage> findAll(){
        return orderOrderImageRepository.findAll();
    }
}
