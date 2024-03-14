package com.chatop.chatopapi.controller;


import com.chatop.chatopapi.dtos.messageDTOs.MessageDto;
import com.chatop.chatopapi.model.Message;
import com.chatop.chatopapi.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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


@Tag(
        name = "CRUD REST APIs for Message processing",
        description = "Provides Create message operation for now"
)
@RestController
@RequestMapping("/api/messages")
public class MessageRestController {


    @Autowired
    MessageService messageService;
    @Autowired
    ModelMapper modelMapper;

    private final Logger logger = LogManager.getLogger(AuthRestController.class);


    @Operation(
            summary = "Send a message REST API",
            description = "endpoint for sending a message that is referenced by the sender to the rental property concerned"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )

    @PostMapping("/")
    public ResponseEntity<Map<String, String>> sendMessage(@RequestBody MessageDto messageDto) {


        Message sentMessage = messageService.sendMessage(messageDto);


        if (messageDto == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(Collections.singletonMap("message", "Message sent with success"));
    }
}
