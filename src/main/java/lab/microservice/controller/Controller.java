package lab.microservice.controller;

import lab.microservice.model.Item;
import lab.microservice.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private Service service;


    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = service.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Optional<Item> item = service.getItemById(id);
        return item.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        if (service.getAllItems().stream().map(Item::getName).filter(name -> item.getName().equals(name)).toList().size() > 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Item createdItem = service.createItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item itemDetails) {
        if (service.getItemById(id).isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Item updatedItem = service.updateItem(id, itemDetails);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {

        if (service.getItemById(id).isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        service.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
