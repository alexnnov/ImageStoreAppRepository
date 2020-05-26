package com.netcracker.controller;

import com.netcracker.entity.Client;

import com.netcracker.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;




    @PostMapping ("/create")
    @ResponseStatus (code = HttpStatus.CREATED)
    public void createBook(@RequestBody Client client) {
        clientService.add(client);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Client> updateShop(@RequestBody Client customer,@PathVariable("id") String id){
        clientService.update(customer);
        return ResponseEntity.ok(customer);
    }


    @DeleteMapping ("/delete/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        clientService.remove(id);
    }



    @GetMapping ("/find")
    public ResponseEntity<Client> findById (@RequestParam int id) {
        Client customer = clientService.find(id);

        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping ("/find/all")
    public List<Client> findAll () {
        return clientService.findAll();
    }


}
