package com.chatop.chatopapi.services;

import com.chatop.chatopapi.domains.models.dtos.messageDTOs.MessageDto;
import com.chatop.chatopapi.domains.models.Message;
import org.springframework.stereotype.Service;


@Service
public interface MessageService {

    Message sendMessage(MessageDto messageDto);
}
