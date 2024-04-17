package com.chatop.chatopapi.domains.models.dtos.rentalDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRentalDto {

    private String name;

    private Double surface;

    private BigDecimal price;

    private String description;

    private Integer ownerId;

}