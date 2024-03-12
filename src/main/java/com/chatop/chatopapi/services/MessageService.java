package com.chatop.chatopapi.services;

import com.chatop.chatopapi.dtos.MessageDto;
import com.chatop.chatopapi.model.Message;
import org.springframework.stereotype.Service;


@Service
public interface MessageService {

    Message sendMessage(MessageDto messageDto);
}
