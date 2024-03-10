package com.chatop.chatopapi.controller;


import com.chatop.chatopapi.dtos.LoginDto;
import com.chatop.chatopapi.dtos.RegisterDto;
import com.chatop.chatopapi.dtos.UserDto;
import com.chatop.chatopapi.model.User;
import com.chatop.chatopapi.responses.TokenResponse;
import com.chatop.chatopapi.services.AuthService;
import com.chatop.chatopapi.services.UserService;
import com.chatop.chatopapi.utils.JWTUtils;
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


@NoArgsConstructor
@RestController
@RequestMapping("/api/auth")
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



    @PostMapping(path = "/register")
    public ResponseEntity<TokenResponse> register(@RequestBody RegisterDto registerDto) {

        User convertedUserDto = convertToUserEntity(registerDto);
        User registeredUser = authService.register(convertedUserDto);
        String token = JWTUtils.generateToken(
                registeredUser.getEmail(),
                registeredUser.getUsername(),
                registeredUser.getId()
        );
        TokenResponse tokenResponse = new TokenResponse(token);

        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginDto loginDto) {

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

    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe(@RequestHeader("Authorization") String authorizationHeader) {
        // get userId in token
        String token = authorizationHeader.substring(7);
        Integer userId = JWTUtils.extractId(token);
        Optional<User> currentUser = userService.getUserByid(userId);

        if (currentUser.isPresent()) {
            UserDto userDto = new UserDto(
                    currentUser.get().getId(),
                    currentUser.get().getUsername(),
                    currentUser.get().getCreatedAt().toLocalDate().toString().replace("-", "/"),
                    currentUser.get().getUpdatedAt().toLocalDate().toString().replace("-", "/")
                    );

            return ResponseEntity.ok(userDto);
        }

        return ResponseEntity.notFound().build();
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
