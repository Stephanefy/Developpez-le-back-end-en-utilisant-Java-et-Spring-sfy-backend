package com.chatop.chatopapi.repository;

import com.chatop.chatopapi.model.Rental;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RentalRepository extends CrudRepository<Rental, Integer> {
    List<Rental> findAll();

}
