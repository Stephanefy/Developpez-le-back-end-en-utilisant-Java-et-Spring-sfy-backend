package com.chatop.chatopapi.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetailsService {

    UserDetails loadUserByUsername(String username);
}
