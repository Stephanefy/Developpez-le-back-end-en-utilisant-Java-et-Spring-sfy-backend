package com.chatop.chatopapi.dtos.authDTOs;

import jakarta.validation.constraints.Email;
import lombok.*;

@Setter
@Getter
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
