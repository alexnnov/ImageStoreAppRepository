package com.netcracker.controller;

import com.netcracker.entity.Theme;

import com.netcracker.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/themes")
public class ThemeController {

    @Autowired
    private ThemeService themeService;




    @PostMapping ("/create")
    @ResponseStatus (code = HttpStatus.CREATED)
    public void createBook(@RequestBody Theme theme) {
        themeService.add(theme);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Theme> updateShop(@RequestBody Theme customer,@PathVariable("id") String id){
        themeService.update(customer);
        return ResponseEntity.ok(customer);
    }


    @DeleteMapping ("/delete/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        themeService.remove(id);
    }



    @GetMapping ("/find")
    public ResponseEntity<Theme> findById (@RequestParam int id) {
        Theme customer = themeService.find(id);

        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping ("/find/all")
    public List<Theme> findAll () {
        return themeService.findAll();
    }


}
