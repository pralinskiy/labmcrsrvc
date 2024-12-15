package lab.microservice.repository;

import lab.microservice.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Item, Long> {



}
