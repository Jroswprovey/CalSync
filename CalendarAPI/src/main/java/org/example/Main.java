/*
    Joseph Rosw-Provey
    3/26/2025

 */

package org.example;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import org.example.CalendarServiceFactory;
import com.google.api.services.calendar.Calendar;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        try {

            DateTime now = new DateTime(System.currentTimeMillis());
            Calendar service = CalendarServiceFactory.getCalendarService();
            Events events = service.events().list("primary")
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
                        "Start Date & Time: " + event.getStart().getDateTime() + " " +
                        "Time Zone: " + event.getStart().getTimeZone()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}