package org.calsync.controller;

import org.calsync.Service.CalendarServiceFactory;
import org.calsync.Service.EmailService;
import org.springframework.web.bind.annotation.*;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CalendarController {

    @GetMapping("/calendar")
    public List<Event> getCalendarSummaries() throws IOException, GeneralSecurityException {
        DateTime now = new DateTime(System.currentTimeMillis());

        Calendar service = CalendarServiceFactory.getCalendarService();

        Events events = service.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        List<Event> eventsList = events.getItems();

        return eventsList;
    }


    @GetMapping("/ping")
    public String test(){
        return "pong";
    }

    @GetMapping("/sendEmail")
    public void sendEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String text
    ){
        EmailService.sendEmail(to, subject, text);
    }



}
