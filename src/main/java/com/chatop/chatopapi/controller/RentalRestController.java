package com.chatop.chatopapi.controller;


import com.chatop.chatopapi.model.Rental;
import com.chatop.chatopapi.services.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rentals")
public class RentalRestController {


    private RentalService rentalService;
    @GetMapping("/")
    public ResponseEntity<List<Rental>> getAllRentals(){
        List<Rental> rentals = rentalService.findAllRentals();
        return new ResponseEntity <List<Rental>>(rentals, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> addNewRental(@RequestBody Rental rental) {

        Rental insertedRental = rentalService.createRental(rental);

        if (insertedRental == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity("{\"message\": \"Rental created\"}", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rental> updateRental(@PathVariable String id, @RequestBody Rental newRental) {

        Rental updatedRental = rentalService.updateRental(id, newRental);

        return new ResponseEntity<Rental>(updatedRental, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rental> findRentalById(@PathVariable String id) {
        Rental foundRental = rentalService.findRentalById(id);

        if (foundRental == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Rental>(foundRental, HttpStatus.OK);
    }
}
