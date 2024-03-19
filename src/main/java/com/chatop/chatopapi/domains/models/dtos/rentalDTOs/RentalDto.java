package com.chatop.chatopapi.domains.models.dtos.rentalDTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;



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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Optional<LocalDateTime> createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Optional<LocalDateTime> updatedAt;


}
