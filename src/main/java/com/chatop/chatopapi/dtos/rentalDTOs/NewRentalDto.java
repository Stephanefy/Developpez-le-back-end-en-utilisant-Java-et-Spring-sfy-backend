package com.chatop.chatopapi.dtos.rentalDTOs;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
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
