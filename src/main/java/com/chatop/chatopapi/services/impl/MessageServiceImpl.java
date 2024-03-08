package com.chatop.chatopapi.services.impl;

import com.chatop.chatopapi.model.Message;
import com.chatop.chatopapi.repository.MessageRepository;
import com.chatop.chatopapi.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;
    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }
}
