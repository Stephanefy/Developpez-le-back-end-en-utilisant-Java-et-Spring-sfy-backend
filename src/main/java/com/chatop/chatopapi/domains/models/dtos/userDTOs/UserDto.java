package com.chatop.chatopapi.domains.models.dtos.userDTOs;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    private String name;

    private String created_at;

    private String update_at;


}
