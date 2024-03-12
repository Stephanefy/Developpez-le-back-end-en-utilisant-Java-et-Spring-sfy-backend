package com.chatop.chatopapi.services.impl;

import com.chatop.chatopapi.dtos.MessageDto;
import com.chatop.chatopapi.exceptions.NotFoundException;
import com.chatop.chatopapi.model.User;
import com.chatop.chatopapi.model.Message;
import com.chatop.chatopapi.model.Rental;
import com.chatop.chatopapi.repository.MessageRepository;
import com.chatop.chatopapi.repository.RentalRepository;
import com.chatop.chatopapi.repository.UserRepository;
import com.chatop.chatopapi.services.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    ModelMapper modelMapper;


    @Autowired
    UserRepository userRepository;

    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    MessageRepository messageRepository;

    public Message sendMessage(MessageDto messageDto) {

        User user = userRepository.findById(messageDto.getUser_id()).orElseThrow();
        Rental rental = rentalRepository.findById(messageDto.getRental_id()).orElseThrow();

        if (user == null || rental == null) {
            throw new NotFoundException("no rental or user with such id exist");
        }

        Message message = modelMapper.map(messageDto, Message.class);
        message.setUserId(user);
        message.setRentalId(rental);
        messageRepository.save(message);


        return messageRepository.save(message);
    }
}
