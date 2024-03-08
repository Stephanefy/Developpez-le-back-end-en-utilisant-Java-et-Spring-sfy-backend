package com.chatop.chatopapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalDto {

    private Long id;

    private String name;

    private Integer surface;

    private Integer price;

    private List<String> picture;

    private String description;

    private Long ownerId;

    private String createdAt;

    private String updatedAt;
}
