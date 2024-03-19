package com.chatop.chatopapi.repository;

import com.chatop.chatopapi.domains.models.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
