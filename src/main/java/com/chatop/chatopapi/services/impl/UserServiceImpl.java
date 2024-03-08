package com.chatop.chatopapi.services.impl;

import com.chatop.chatopapi.model.User;
import com.chatop.chatopapi.repository.UserRepository;
import com.chatop.chatopapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository; // Assuming you have a user repository


    @Override
    public User getUserDetails() {
        // Get the authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extract username or user ID from authentication object
        String username = authentication.getName();

        // Fetch and return the user details from the repository
        return userRepository.findByUsername(username).orElseThrow();
    }

    @Override
    public Optional<User> getUserByid(Integer id) {
        return userRepository.findById(id);
    }
}
