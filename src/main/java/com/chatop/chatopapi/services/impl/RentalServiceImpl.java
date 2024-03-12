package com.chatop.chatopapi.services.impl;

import com.chatop.chatopapi.model.Rental;
import com.chatop.chatopapi.repository.RentalRepository;
import com.chatop.chatopapi.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    @Override
    public List<Rental> findAllRentals() {
        return rentalRepository.findAll();
    }


    @Override
    public Rental findRentalById(String rentalId) {
        Integer parsedId = Integer.valueOf(rentalId);
        return rentalRepository.findById(parsedId).get();
    }

    @Override
    public Rental createRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    @Override
    public Rental updateRental(String id, Rental newRental) {

        Integer parsedId = Integer.valueOf(id);

        return rentalRepository.findById(parsedId).map(rental -> {
            rental.setName(newRental.getName());
            rental.setPrice(newRental.getPrice());
            rental.setDescription(newRental.getDescription());
            rental.setSurface(newRental.getSurface());

            return rentalRepository.save(rental);
        }).orElseGet(() -> rentalRepository.save(newRental));

    }
}
