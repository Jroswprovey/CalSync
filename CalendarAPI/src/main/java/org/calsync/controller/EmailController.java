package org.calsync.controller;

import org.calsync.Service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class EmailController {


    @PostMapping("/sendEmail")
    public void sendEmail( @RequestBody EmailService emailService) {

        EmailService.sendEmail("jroswprovey@gmail.com","Test","testing");
    }
}
