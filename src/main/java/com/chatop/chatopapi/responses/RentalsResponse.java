package com.chatop.chatopapi.responses;

import com.chatop.chatopapi.dtos.RentalDto;

import java.util.List;

public class RentalsResponse {
    private List<RentalDto> rentals;

    public RentalsResponse() {
    }

    public RentalsResponse(List<RentalDto> rentals) {
        this.rentals = rentals;
    }

    public List<RentalDto> getRentals() {
        return rentals;
    }

    public void setRentals(List<RentalDto> rentals) {
        this.rentals = rentals;
    }
}

