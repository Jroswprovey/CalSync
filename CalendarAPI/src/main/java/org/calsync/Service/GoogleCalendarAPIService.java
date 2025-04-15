package org.calsync.Service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Events;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class GoogleCalendarAPIService {

    private static final String APPLICATION_NAME = "MyCalendarApp";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    // For read/write, use CalendarScopes.CALENDAR, or for read-only, use CALENDAR_READONLY.
    // You can add more scopes in the list if needed.
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);

    // Adjust the path if your credentials.json is in another location.
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    // Returns an authorized Calendar client service.
    public static Calendar getCalendarService() throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        var httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Credential credential = getCredentials(httpTransport);
        return new Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    // Helper method to load credential from credentials.json and handle OAuth flow.
    private static Credential getCredentials(final com.google.api.client.http.javanet.NetHttpTransport httpTransport)
            throws IOException {

        // Load client secrets from the resource file.
        InputStream in = GoogleCalendarAPIService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new IOException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public static Events getEvents(int maxResults, DateTime dateTime,String query) throws GeneralSecurityException, IOException {

        Calendar service = GoogleCalendarAPIService.getCalendarService();

        return service.events().list("primary")
                .setQ(query)
                .setMaxResults(maxResults)
                .setTimeMin(dateTime)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

    }













}