package org.example;

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



}
