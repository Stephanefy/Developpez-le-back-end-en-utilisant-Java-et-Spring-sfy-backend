package com.chatop.chatopapi.domains.models.dtos.messageDTOs;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {

    private Long id;

    private String message;

    private Integer user_id;

    private Integer rental_id;
}
