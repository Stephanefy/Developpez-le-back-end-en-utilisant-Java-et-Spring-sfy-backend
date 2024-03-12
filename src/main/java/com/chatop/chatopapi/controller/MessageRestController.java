package com.chatop.chatopapi.controller;


import com.chatop.chatopapi.dtos.MessageDto;
import com.chatop.chatopapi.model.Message;
import com.chatop.chatopapi.services.MessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/messages")
public class MessageRestController {


    @Autowired
    MessageService messageService;
    @Autowired
    ModelMapper modelMapper;

    private final Logger logger = LogManager.getLogger(AuthRestController.class);


    @PostMapping("/")
    public ResponseEntity<Map<String, String>> sendMessage(@RequestBody MessageDto messageDto) {


        Message sentMessage = messageService.sendMessage(messageDto);


        if (messageDto == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(Collections.singletonMap("message", "Message sent with success"));
    }
}
