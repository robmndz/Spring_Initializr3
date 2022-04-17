package com.example.spring_initializr3.Repositories;

import com.example.spring_initializr3.Models.Produkt;
import org.springframework.data.repository.CrudRepository;

public interface ProduktRepository extends CrudRepository<Produkt, Long> {
}
