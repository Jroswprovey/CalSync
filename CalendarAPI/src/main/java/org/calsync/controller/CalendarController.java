/*
* Joseph Rosw-Provey
*
* Spring boot application that handles everything related to the calendar
*
* */

package org.calsync.controller;


import org.calsync.DTO.CalendarRequest;
import org.calsync.Service.CalendarService;
import org.springframework.web.bind.annotation.*;
import com.google.api.services.calendar.model.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class CalendarController {

    @PostMapping("/calendar")
    public List<Event> getCalendarSummaries( @RequestBody CalendarRequest calendarRequest) throws IOException, GeneralSecurityException {
        return CalendarService.getEvents(
                calendarRequest.getMaxResults(),
                calendarRequest.getDateTime(),
                calendarRequest.getQuery())
                .getItems();
    }


}
