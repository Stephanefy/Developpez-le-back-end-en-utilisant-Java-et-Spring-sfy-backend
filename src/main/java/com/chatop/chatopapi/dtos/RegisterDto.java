package com.chatop.chatopapi.dtos;

import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterDto {

    private Integer id;

    private String email;

    private String username;

    private String password;
}
