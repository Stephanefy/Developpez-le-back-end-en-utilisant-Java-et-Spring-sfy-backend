package com.chatop.chatopapi.controllers;


import com.chatop.chatopapi.domains.models.dtos.rentalDTOs.NewRentalDto;
import com.chatop.chatopapi.domains.models.dtos.rentalDTOs.RentalDto;
import com.chatop.chatopapi.exceptions.NotFoundException;
import com.chatop.chatopapi.domains.models.Rental;
import com.chatop.chatopapi.responses.RentalsResponse;
import com.chatop.chatopapi.services.RentalService;
import com.chatop.chatopapi.services.StorageService;
import com.chatop.chatopapi.utils.JWTUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(
        name = "CRUD REST APIs for Rental data processing",
        description = "Provides CREATE, GET and PUT operations for rental properties"
)
@RestController
@RequestMapping("/rentals")
public class RentalRestController {

    private final Logger logger = LogManager.getLogger(AuthRestController.class);

    @Autowired
    private RentalService rentalService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StorageService storageService;

    @Operation(
            summary = "Get all rental properties REST API",
            description = "This endpoint return all rental properties recorded in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping
    public ResponseEntity<RentalsResponse> getAllRentals() {
        List<Rental> rentals = rentalService.findAllRentals();

        List<RentalDto> rentalsDto = (List<RentalDto>) rentals.stream().map(rental -> modelMapper.map(rental, RentalDto.class)).collect(Collectors.toList());

        RentalsResponse response = new RentalsResponse(rentalsDto);

        return ResponseEntity.ok(response);
    }
    @Operation(
            summary = "add a new rental property REST API",
            description = "Endpoint for creating a new rental property in the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping(path = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Map<String, String>> addNewRental(@RequestParam("name") String name, @RequestParam("surface") Double surface, @RequestParam("price") BigDecimal price, @RequestParam("description") String description, @RequestPart(value = "picture", required = false) MultipartFile picture, @RequestHeader("Authorization") String authorizationHeader) {

        // get owner_id in token
        String token = authorizationHeader.substring(7);
        Integer ownerId = JWTUtils.extractId(token);

        // store image locally and get picture relative path
        String absolutePath = (picture != null ? storageService.store(picture) : "No picture" );
        String pictureRelativePath = convertPictureToRelativePath(absolutePath);

        logger.info("Absolute Path {}", absolutePath);

        // create a Rental DTO from request params
        NewRentalDto rentalDto = new NewRentalDto(name, surface, price, pictureRelativePath, description, ownerId);

        // convert DTO to Entity then save it in the DB
        Rental rentalEntity = modelMapper.map(rentalDto, Rental.class);
        Rental insertedRental = rentalService.createRental(rentalEntity);

        if (insertedRental == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Collections.singletonMap("key", "value");

        return ResponseEntity.ok(Collections.singletonMap("message", "rental created"));
    }
    @Operation(
            summary = "Update a rental property REST API",
            description = "endpoint for updating a rental property that is retrieved by its ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateRental(@PathVariable String id, @RequestParam("name") String name, @RequestParam("surface") Double surface, @RequestParam("price") BigDecimal price, @RequestParam("description") String description, @RequestPart(value = "picture", required = false) MultipartFile picture, @RequestHeader("Authorization") String authorizationHeader) {


        String absolutePath = (picture != null ? storageService.store(picture) : "No picture" );
        String pictureRelativePath = convertPictureToRelativePath(absolutePath);

        NewRentalDto newRentalDto = new NewRentalDto(name, surface, price, pictureRelativePath, description, Integer.parseInt(id));
        Rental newRentalEntity = modelMapper.map(newRentalDto, Rental.class);

        Optional<Rental> updatedRental = rentalService.updateRental(id, newRentalEntity);

        logger.info("updated Rental {}", updatedRental);


        return ResponseEntity.ok(Collections.singletonMap("message", "rental updated"));
    }
    @Operation(
            summary = "Retrieve a rental property details REST API",
            description = "endpoint for retrieving information about a particular rental property"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )

    @GetMapping("/{id}")
    public ResponseEntity<RentalDto> findRentalById(@PathVariable String id) throws NotFoundException {
        Rental foundRental = rentalService.findRentalById(id);
        RentalDto rentalDto = modelMapper.map(foundRental, RentalDto.class);

        return ResponseEntity.ok(rentalDto);
    }


    private String convertPictureToRelativePath(String absolutePath) {
        String[] parts = absolutePath.split("src/main/resources");
        return parts[1];
    }
}
