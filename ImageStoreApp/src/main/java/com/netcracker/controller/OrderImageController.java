package com.netcracker.controller;

import com.netcracker.entity.OrderImage;

import com.netcracker.service.OrderImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/orderImages")
public class OrderImageController {

    @Autowired
    private OrderImageService orderImageService;




    @PostMapping ("/create")
    @ResponseStatus (code = HttpStatus.CREATED)
    public void createBook(@RequestBody OrderImage orderImage) {
        orderImageService.add(orderImage);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<OrderImage> updateShop(@RequestBody OrderImage customer,@PathVariable("id") String id){
        orderImageService.update(customer);
        return ResponseEntity.ok(customer);
    }


    @DeleteMapping ("/delete/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        orderImageService.remove(id);
    }



    @GetMapping ("/find")
    public ResponseEntity<OrderImage> findById (@RequestParam int id) {
        OrderImage customer = orderImageService.find(id);

        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping ("/find/all")
    public List<OrderImage> findAll () {
        return orderImageService.findAll();
    }


}
