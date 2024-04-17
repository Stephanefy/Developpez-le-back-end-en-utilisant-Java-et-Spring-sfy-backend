package com.chatop.chatopapi.services.impl;

import com.chatop.chatopapi.domains.models.User;
import com.chatop.chatopapi.repository.UserRepository;
import com.chatop.chatopapi.services.AuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {

    private final Logger logger = LogManager.getLogger(AuthServiceImpl.class);

    private final PasswordEncoder passwordEncoder;


    @Autowired
    private AuthenticationManager authenticationManager;



    public AuthServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User registeredUser = userRepository.save(user);
        logger.info("reached");
        return registeredUser;
    }

}
