package com.chatop.chatopapi.domains.models.dtos.rentalDTOs;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewRentalDto {

    private String name;

    private Double surface;

    private BigDecimal price;

    private String picture;

    private String description;

    private Integer ownerId;
}
