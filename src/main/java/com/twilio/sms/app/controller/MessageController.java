package com.twilio.sms.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.sms.app.model.MessageRequest;
import com.twilio.sms.app.service.MessageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/message")
public class MessageController {

    private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@Valid @RequestBody MessageRequest request) throws Exception, MethodArgumentNotValidException {

    	LOG.info("Request - Body : {}, Sent To : {}", request.getMessageBody(), request.getMessageNumber());
    	
        messageService.sendMessage(request);
        return new ResponseEntity<>("Message Successfully Sent", HttpStatus.OK);
    }

}
