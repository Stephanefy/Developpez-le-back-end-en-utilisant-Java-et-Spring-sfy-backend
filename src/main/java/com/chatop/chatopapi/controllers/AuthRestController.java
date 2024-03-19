package com.chatop.chatopapi.controllers;


import com.chatop.chatopapi.domains.models.User;
import com.chatop.chatopapi.domains.models.dtos.authDTOs.LoginDto;
import com.chatop.chatopapi.domains.models.dtos.authDTOs.RegisterDto;
import com.chatop.chatopapi.domains.models.dtos.userDTOs.UserDto;
import com.chatop.chatopapi.exceptions.NotFoundException;
import com.chatop.chatopapi.responses.TokenResponse;
import com.chatop.chatopapi.services.AuthService;
import com.chatop.chatopapi.services.UserService;
import com.chatop.chatopapi.utils.JWTUtils;
import io.jsonwebtoken.MalformedJwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Tag(
        name = "Authentication API",
        description = "Provides CRUD operations for user authentication, including user registration, user login, and retrieving current user information."
)
@NoArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthRestController {


    private final Logger logger = LogManager.getLogger(AuthRestController.class);


    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(
            summary = "Register Authentication REST API",
            description = "Register User REST API is used to save user in the database and generate a JWT token that is return in the response"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping(path = "/register")
    public ResponseEntity<TokenResponse> register(@Valid @RequestBody  RegisterDto registerDto)  {

        User convertedUserDto = convertToUserEntity(registerDto);
        User registeredUser = authService.register(convertedUserDto);
        String token = JWTUtils.generateToken(registeredUser.getEmail(), registeredUser.getUsername(), registeredUser.getId());
        TokenResponse tokenResponse = new TokenResponse(token);

        return ResponseEntity.ok(tokenResponse);
    }
    @Operation(
            summary = "Login Authentication REST API",
            description = "Login User REST API is used to authenticate user in the database and generate a JWT token that is return in the response"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginDto loginDto) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getLogin(), loginDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw e;
        }
        User loggedInUser = userService.getUserDetails(loginDto.getLogin());
        String token = JWTUtils.generateToken(loggedInUser.getEmail(), loggedInUser.getUsername(), loggedInUser.getId());
        TokenResponse tokenResponse = new TokenResponse(token);


        return ResponseEntity.ok(tokenResponse);
    }



    @Operation(
            summary = "Get me Authentication REST API",
            description = "Get currently authenticated user details REST API, return user details"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe(@RequestHeader("Authorization") String authorizationHeader) throws NotFoundException {
        ;
        // get userId in token
        try {
            String token = authorizationHeader.substring(7);
            Integer userId = JWTUtils.extractId(token);
            Optional<User> currentUser = userService.getUserById(userId);

            if (currentUser.isPresent()) {
                UserDto userDto = new UserDto(currentUser.get().getId(), currentUser.get().getUsername(), currentUser.get().getCreatedAt().toLocalDate().toString().replace("-", "/"), currentUser.get().getUpdatedAt().toLocalDate().toString().replace("-", "/"));

                return ResponseEntity.ok(userDto);
            }

        } catch (MalformedJwtException e) {
            logger.info("MALFORMED JWT TOKEN {}", e );
        }

        return ResponseEntity.noContent().build();
    }


    private RegisterDto convertToUserDto(User user) {
        RegisterDto registerDto = modelMapper.map(user, RegisterDto.class);

        return registerDto;
    }

    private User convertToUserEntity(RegisterDto registerDto) {

        User user = modelMapper.map(registerDto, User.class);


//        if (userDto.getId() != null) {
//            User oldUser = userService.getUserById(userDto.getId());
//        }
        return user;
    }
}
