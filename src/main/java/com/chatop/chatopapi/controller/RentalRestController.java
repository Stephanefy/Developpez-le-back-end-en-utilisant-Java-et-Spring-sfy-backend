package com.chatop.chatopapi.controller;


import com.chatop.chatopapi.dtos.NewRentalDto;
import com.chatop.chatopapi.dtos.RentalDto;
import com.chatop.chatopapi.model.Rental;
import com.chatop.chatopapi.responses.RentalsResponse;
import com.chatop.chatopapi.services.RentalService;
import com.chatop.chatopapi.services.StorageService;
import com.chatop.chatopapi.utils.JWTUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/rentals")
public class RentalRestController {

    private final Logger logger = LogManager.getLogger(AuthRestController.class);


    @Autowired
    private RentalService rentalService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StorageService storageService;

    @GetMapping("")
    public ResponseEntity<RentalsResponse> getAllRentals(){
        List<Rental> rentals = rentalService.findAllRentals();

        List<RentalDto> rentalsDto = (List<RentalDto>) rentals.stream().map(
                rental -> modelMapper.map(rental, RentalDto.class)).collect(Collectors.toList());

        RentalsResponse response = new RentalsResponse(rentalsDto);

        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> addNewRental(@RequestParam("name") String name,
                                               @RequestParam("surface") Double surface,
                                               @RequestParam("price") BigDecimal price,
                                               @RequestParam("description") String description,
                                               @RequestPart(value = "picture", required = false) MultipartFile picture,
                                               @RequestHeader("Authorization") String authorizationHeader
                                               ) {

        // get owner_id in token
        String token = authorizationHeader.substring(7);
        Integer ownerId = JWTUtils.extractId(token);

        // get picture file path
        String  picturePath = (picture != null ? picture.getOriginalFilename() : "No picture uploaded");

        // create a Rental DTO from request params
        NewRentalDto rentalDto = new NewRentalDto(name,surface,price,picturePath,description, ownerId);

        // convert DTO to Entity then save it in the DB
        Rental rentalEntity = modelMapper.map(rentalDto, Rental.class);
        Rental insertedRental = rentalService.createRental(rentalEntity);

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
