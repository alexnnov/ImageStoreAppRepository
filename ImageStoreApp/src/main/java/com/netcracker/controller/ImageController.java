package com.netcracker.controller;

import com.netcracker.entity.Image;

import com.netcracker.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;




    @PostMapping ("/create")
    @ResponseStatus (code = HttpStatus.CREATED)
    public void createBook(@RequestBody Image image) {
        imageService.add(image);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Image> updateShop(@RequestBody Image customer,@PathVariable("id") String id){
        imageService.update(customer);
        return ResponseEntity.ok(customer);
    }


    @DeleteMapping ("/delete/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        imageService.remove(id);
    }



    @GetMapping ("/find")
    public ResponseEntity<Image> findById (@RequestParam int id) {
        Image customer = imageService.find(id);

        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping ("/find/all")
    public List<Image> findAll () {
        return imageService.findAll();
    }


}
