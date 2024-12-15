package lab.microservice.service;


import lab.microservice.model.Item;
import lab.microservice.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Repository repository;

    public List<Item> getAllItems() {
        return repository.findAll();
    }

    public Optional<Item> getItemById(Long id) {
        return repository.findById(id);
    }

    public Item createItem(Item item) {
        return repository.save(item);
    }

    public Item updateItem(Long id, Item itemDetails) {
        Item item = repository.findById(id).orElseThrow();
        item.setName(itemDetails.getName());
        item.setInfo(itemDetails.getInfo());
        return repository.save(item);
    }

    public void deleteItem(Long id) {
        Item item = repository.findById(id).orElseThrow();
        repository.delete(item);
    }

}
