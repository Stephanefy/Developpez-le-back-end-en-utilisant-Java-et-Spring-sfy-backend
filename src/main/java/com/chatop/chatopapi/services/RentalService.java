package com.chatop.chatopapi.services;

import com.chatop.chatopapi.model.Rental;

import java.util.List;

public interface RentalService {

     List<Rental> findAllRentals();

     Rental findRentalById(String rentalId);

     Rental createRental(Rental rental);

     Rental updateRental(String id , Rental rental);

}
