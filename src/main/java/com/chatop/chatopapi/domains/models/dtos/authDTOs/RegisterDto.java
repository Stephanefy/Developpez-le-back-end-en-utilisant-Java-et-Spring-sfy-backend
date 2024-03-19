package com.chatop.chatopapi.domains.models.dtos.authDTOs;

import jakarta.validation.constraints.Email;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterDto {

    @Email
    private String email;

    private String username;

    private String password;
}
