package com.netcracker.controller;

import com.netcracker.entity.Order;

import com.netcracker.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;




    @PostMapping ("/create")
    @ResponseStatus (code = HttpStatus.CREATED)
    public void createBook(@RequestBody Order order) {
        orderService.add(order);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Order> updateShop(@RequestBody Order customer,@PathVariable("id") String id){
        orderService.update(customer);
        return ResponseEntity.ok(customer);
    }


    @DeleteMapping ("/delete/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        orderService.remove(id);
    }



    @GetMapping ("/find")
    public ResponseEntity<Order> findById (@RequestParam int id) {
        Order customer = orderService.find(id);

        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping ("/find/all")
    public List<Order> findAll () {
        return orderService.findAll();
    }


}
