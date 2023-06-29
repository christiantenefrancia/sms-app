package com.twilio.sms.app.model;

import jakarta.validation.constraints.NotEmpty;

public class MessageRequest {

    public String messageBody;
    
    @NotEmpty(message = "Number is required.")
    public String messageNumber;

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(String messageNumber) {
        this.messageNumber = messageNumber;
    }
}
