package com.chatop.chatopapi.services;

import com.chatop.chatopapi.model.User;

public interface JWTService {

    public String generateToken(User userDetails);
}
