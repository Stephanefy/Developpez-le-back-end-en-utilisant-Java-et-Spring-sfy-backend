package com.chatop.chatopapi.controllers;

import com.chatop.chatopapi.domains.models.dtos.userDTOs.UserDetailsDto;
import com.chatop.chatopapi.domains.models.User;
import com.chatop.chatopapi.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@Tag(
        name = "CRUD REST APIs for user data processing",
        description = "Provides GET operation for users"
)
@NoArgsConstructor
@RestController
@RequestMapping("/user")
public class UserRestController {


    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;
    @Operation(
            summary = "Get a user details REST API",
            description = "Endpoint for returning user details"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailsDto> getUserById(@PathVariable String userId) {
        int parsedUserId;
        try {
            parsedUserId = Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User ID must be an integer");
        }

        User user = userService.getUserById(parsedUserId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        UserDetailsDto userDetailsDto = modelMapper.map(user, UserDetailsDto.class);

        return ResponseEntity.ok(userDetailsDto);
    }
}
