package com.chatop.chatopapi.services;

import com.chatop.chatopapi.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

     User getUserDetails();

     <Optional> java.util.Optional<User> getUserByid(Integer id);


}
