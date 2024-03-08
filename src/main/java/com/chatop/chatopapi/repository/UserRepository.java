package com.chatop.chatopapi.repository;


import com.chatop.chatopapi.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
