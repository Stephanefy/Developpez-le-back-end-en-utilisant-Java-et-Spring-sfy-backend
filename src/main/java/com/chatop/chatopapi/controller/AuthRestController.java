package com.chatop.chatopapi.controller;


import com.chatop.chatopapi.dtos.LoginDto;
import com.chatop.chatopapi.dtos.RegisterDto;
import com.chatop.chatopapi.model.User;
import com.chatop.chatopapi.responses.TokenResponse;
import com.chatop.chatopapi.services.AuthService;
import com.chatop.chatopapi.services.JWTService;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@NoArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthRestController {


    private final Logger logger = LogManager.getLogger(AuthRestController.class);


    @Autowired
    private JWTService jwtService;


    @Autowired
    private AuthService authService;

    @Autowired
    private ModelMapper modelMapper;



    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody RegisterDto registerDto) {

        User convertedUserDto = convertToUserEntity(registerDto);
        User registeredUser = authService.register(convertedUserDto);
        String token = jwtService.generateToken(registeredUser);
        TokenResponse tokenResponse = new TokenResponse(token);

        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginDto loginDto) {

        User loggedInUser = authService.login(loginDto);
        String token = jwtService.generateToken(loggedInUser);
        TokenResponse tokenResponse = new TokenResponse(token);


        return ResponseEntity.ok(tokenResponse);
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
