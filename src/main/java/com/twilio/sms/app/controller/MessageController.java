package com.twilio.sms.app.controller;

import com.twilio.sms.app.model.MessageRequest;
import com.twilio.sms.app.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/message")
public class MessageController {

    private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody MessageRequest request) {

    	LOG.info("Request - Body : {}, Sent To : {}", request.getMessageBody(), request.getMessageNumber());
        boolean response = messageService.sendMessage(request);

        return new ResponseEntity<>("Successfully Sent", HttpStatus.OK);
    }

}
