package com.example.spring_initializr3.Repositories;

import com.example.spring_initializr3.Models.BuyOrder;
import org.springframework.data.repository.CrudRepository;

public interface BuyOrderRepository extends CrudRepository<BuyOrder, Long> {
}
