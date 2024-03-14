package com.chatop.chatopapi.dtos.userDTOs;

import lombok.*;

@Setter
@Getter
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
