package com.chatop.chatopapi.services;


import com.chatop.chatopapi.domains.models.User;

public interface AuthService {

    User register(User user);

//    User login(LoginDto loginDto);
}
