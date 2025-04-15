/*
//Joseph Rosw-Provey
3/26/2025

This is a demo class and will not be used in the end product and is just a POC

*/

package org.calsync;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.*;
import org.calsync.Service.GoogleCalendarAPIService;

import java.util.List;


public class DemoClass {

        public static void main(String[] args) {
            try {

                DateTime now = new DateTime(System.currentTimeMillis());
                Calendar service = GoogleCalendarAPIService.getCalendarService();

                CalendarList calendarList = service.calendarList().list().execute();
                List<CalendarListEntry> calendarListItems = calendarList.getItems();

                //Loops through the calendar list and Gets the name (Summary) and its ID.
                for (CalendarListEntry listEntry : calendarListItems){
                    System.out.println(
                            "Name: " +listEntry.getSummary() + " " +
                                    "Id: " + listEntry.getId()
                    );
                }

                //Pulls events from Google using a calendar ID and puts that data into eventlist
                Events events = service.events().list("lstd69l1aru7q4hc4g7drourh3fm86sd@import.calendar.google.com")
                        .setMaxResults(10)
                        .setTimeMin(now)
                        .setOrderBy("startTime")
                        .setSingleEvents(true)
                        .execute();
                List<Event> eventsList = events.getItems();

                //goes through every event and prints out Name (or summary as google calls it) then gets date and time
                for (Event event : eventsList) {
                    System.out.println(
                            "Name: " + event.getSummary() + " " +
                                    "Start Date & Time: " + event.getStart().getDateTime().toStringRfc3339() + " " +
                                    "Time Zone: " + event.getStart().getTimeZone()
                    );
                }

                //creating a new Test event
                Event testEvent = new Event();

                //Creates a new EVENT date time object to be used in the event creation
                EventDateTime start = new EventDateTime().setDateTime(now);

                //setting parameters for event
                testEvent.setSummary("Test Event")
                        .setStart(start)
                        .setEnd(start);

                //sending event off to google
                service.events().insert("primary", testEvent).execute();



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


