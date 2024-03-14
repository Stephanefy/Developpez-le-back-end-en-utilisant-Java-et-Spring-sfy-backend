package com.chatop.chatopapi.services;

import com.chatop.chatopapi.exceptions.NotFoundException;
import com.chatop.chatopapi.model.Rental;

import java.util.List;
import java.util.Optional;

public interface RentalService {

     List<Rental> findAllRentals();

     Rental findRentalById(String rentalId) throws NotFoundException;

     Rental createRental(Rental rental);

     Optional<Rental> updateRental(String id , Rental rental);

}
