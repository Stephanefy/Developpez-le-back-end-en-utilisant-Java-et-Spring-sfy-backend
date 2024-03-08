package com.chatop.chatopapi.controller;


import com.chatop.chatopapi.model.Message;
import com.chatop.chatopapi.services.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageRestController {


    private MessageService messageService;
    @PostMapping("/")
    public ResponseEntity<String> sendMessage(@RequestBody Message message) {
        Message sentMessage = messageService.sendMessage(message);

        if (sentMessage == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity("{\"message\": \"Message sent with success\"}", HttpStatus.CREATED);
    }
}
