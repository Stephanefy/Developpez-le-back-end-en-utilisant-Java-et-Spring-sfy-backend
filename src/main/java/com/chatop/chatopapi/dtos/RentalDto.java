package com.chatop.chatopapi.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.util.Optional;


@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalDto {

    private Optional<Integer> id;

    private String name;

    private Double surface;

    private BigDecimal price;

    private String picture;

    private String description;

    private Integer ownerId;

    private Optional<String> createdAt;

    private Optional<String> updatedAt;


}
