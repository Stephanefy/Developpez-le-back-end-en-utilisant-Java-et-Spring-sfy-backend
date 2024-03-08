package com.chatop.chatopapi.services;

import com.chatop.chatopapi.model.Rental;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RentalService {

     List<Rental> findAllRentals();

     Rental findRentalById(String rentalId);

     Rental createRental(Rental rental);

     Rental updateRental(String id , Rental rental);

}
