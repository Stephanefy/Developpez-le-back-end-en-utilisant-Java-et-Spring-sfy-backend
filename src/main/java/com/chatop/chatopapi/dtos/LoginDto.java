package com.chatop.chatopapi.dtos;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private String login;
    private String password;


    // Getters and Setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // toString, hashCode, equals methods could be added for completeness
}
