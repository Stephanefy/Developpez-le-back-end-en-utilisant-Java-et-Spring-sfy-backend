package com.chatop.chatopapi.dtos.messageDTOs;

import lombok.*;

@Getter
@Setter
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
