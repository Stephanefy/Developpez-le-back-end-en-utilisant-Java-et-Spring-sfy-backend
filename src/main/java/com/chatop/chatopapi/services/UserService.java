package com.chatop.chatopapi.services;

import com.chatop.chatopapi.exceptions.NotFoundException;
import com.chatop.chatopapi.domains.models.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

     User getUserDetails(String email);

     Optional<User> getUserById(Integer id) throws NotFoundException;




}
