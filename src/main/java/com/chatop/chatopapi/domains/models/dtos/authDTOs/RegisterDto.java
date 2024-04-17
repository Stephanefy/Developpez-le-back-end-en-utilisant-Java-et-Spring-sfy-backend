package com.chatop.chatopapi.domains.models.dtos.authDTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterDto {

    @Email
    private String email;
    @JsonProperty("name")
    private String username;

    private String password;
}
