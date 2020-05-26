package com.netcracker.controller;

import com.netcracker.entity.ImageTheme;

import com.netcracker.service.ImageThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/imageThemes")
public class ImageThemeController {

    @Autowired
    private ImageThemeService imageThemeService;




    @PostMapping ("/create")
    @ResponseStatus (code = HttpStatus.CREATED)
    public void createBook(@RequestBody ImageTheme imageTheme) {
        imageThemeService.add(imageTheme);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<ImageTheme> updateShop(@RequestBody ImageTheme customer,@PathVariable("id") String id){
        imageThemeService.update(customer);
        return ResponseEntity.ok(customer);
    }


    @DeleteMapping ("/delete/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        imageThemeService.remove(id);
    }



    @GetMapping ("/find")
    public ResponseEntity<ImageTheme> findById (@RequestParam int id) {
        ImageTheme customer = imageThemeService.find(id);

        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping ("/find/all")
    public List<ImageTheme> findAll () {
        return imageThemeService.findAll();
    }


}
