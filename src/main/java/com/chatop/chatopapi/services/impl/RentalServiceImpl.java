package com.chatop.chatopapi.services.impl;

import com.chatop.chatopapi.exceptions.NotFoundException;
import com.chatop.chatopapi.domains.models.Rental;
import com.chatop.chatopapi.repository.RentalRepository;
import com.chatop.chatopapi.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        Rental rental;
        if (rentalRepository.findById(parsedId).isEmpty()) {
            throw new NotFoundException("No rental property found");
        } else {
            rental = rentalRepository.findById(parsedId).get();
        }

        return rental;

    }

    @Override
    public Rental createRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    @Override
    public Optional<Rental> updateRental(String id, Rental newRental) {

        Integer parsedId = Integer.valueOf(id);

        Optional<Rental> updatedRental = rentalRepository.findById(parsedId);


        updatedRental.map(rental -> {
            rental.setName(newRental.getName());
            rental.setPrice(newRental.getPrice());
            rental.setDescription(newRental.getDescription());
            rental.setSurface(newRental.getSurface());

            return rentalRepository.save(rental);
        }).orElseThrow();

        return updatedRental;
    }
}
