package org.calsync.DTO;

import com.google.api.client.util.DateTime;

public class CalendarRequest {
    private String query;
    private int maxResults;
    private DateTime dateTime;
    private String orderBy;

    //getters
    public String getQuery() {
        return query;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public String getOrderBy() {
        return orderBy;
    }

    //setters
    public void setQuery(String query) {
        this.query = query;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public void setDateTime(String dateTime) {
        //to send a datetime from "javascript do this  dateTime: new Date().toISOString()"
        this.dateTime = new DateTime(dateTime);
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
