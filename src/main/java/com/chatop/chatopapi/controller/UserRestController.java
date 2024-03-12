package com.chatop.chatopapi.controller;

import com.chatop.chatopapi.dtos.UserDetailsDto;
import com.chatop.chatopapi.model.User;
import com.chatop.chatopapi.services.UserService;
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

@NoArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserRestController {


    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailsDto> getUserById(@PathVariable String userId) {
        int parsedUserId;
        try {
            parsedUserId = Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User ID must be an integer");
        }

        User user = userService.getUserByid(parsedUserId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        UserDetailsDto userDetailsDto = modelMapper.map(user, UserDetailsDto.class);

        return ResponseEntity.ok(userDetailsDto);
    }
}
