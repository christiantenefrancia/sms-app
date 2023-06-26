package com.twilio.sms.app.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.sms.app.model.MessageRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	
	private static final Logger LOG = LoggerFactory.getLogger(MessageService.class);

    @Value("${twilio.sms.sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.sms.token}")
    private String AUTH_TOKEN;


    public boolean sendMessage(MessageRequest request) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(request.getMessageNumber()),
                new com.twilio.type.PhoneNumber("+15417222134"),
                request.getMessageBody())
                .create();

        LOG.info("Response - {}", message.getSid());

        return true;
    }
}
