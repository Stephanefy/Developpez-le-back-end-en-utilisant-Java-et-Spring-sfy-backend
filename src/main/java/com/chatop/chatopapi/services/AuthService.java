package com.chatop.chatopapi.services;


import com.chatop.chatopapi.dtos.LoginDto;
import com.chatop.chatopapi.model.User;

public interface AuthService {

    User register(User user);

    User login(LoginDto loginDto);
}
