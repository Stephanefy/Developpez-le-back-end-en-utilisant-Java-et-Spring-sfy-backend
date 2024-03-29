package com.chatop.chatopapi.services.impl;

import com.chatop.chatopapi.exceptions.NotFoundException;
import com.chatop.chatopapi.domains.models.User;
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
    public User getUserDetails(String email) {
        // Get the authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Fetch and return the user details from the repository
        return userRepository.findByEmail(email).orElseThrow();
    }

    @Override
    public Optional<User> getUserById(Integer id) throws NotFoundException {
        return userRepository.findById(id);
    }
}
