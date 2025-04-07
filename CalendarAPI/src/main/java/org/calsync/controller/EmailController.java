package org.calsync.controller;

import org.calsync.DTO.EmailRequest;
import org.calsync.Service.EmailService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class EmailController {


    @PostMapping("/sendEmail")
    public void sendEmail( @RequestBody EmailRequest emailRequest) {

        EmailService.sendEmail(
                emailRequest.getRecipient(),
                emailRequest.getSubject(),
                emailRequest.getText());
    }
}
