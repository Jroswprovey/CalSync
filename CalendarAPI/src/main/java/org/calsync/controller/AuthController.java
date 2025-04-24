// Alex
// Accepts a POST /api/auth/login request
// Reads email + password from the frontend
// Validates credentials (mock logic for now)

// To do: Start Spring Boot backend
//  so React (front end) login page can send the email and password to
// the backend to log the user in.

package org.calsync.controller;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import org.calsync.DTO.UserRequest;
import org.calsync.Service.MongoDBService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // allow React frontend
public class AuthController {

    @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody UserRequest userRequest) {
        String email = userRequest.getEmail();
        String password = userRequest.getPassword();
        String accessToken = userRequest.getAccessToken();


        if (MongoDBService.userExist(email)) {
            if (Objects.equals(MongoDBService.getPass(email), password)){

                //creates an authorized credential from the token received from the front end
                Credential credential = new Credential(BearerToken.authorizationHeaderAccessMethod())
                        .setAccessToken(accessToken);

                //creates an authorized calendar object that can be used to query that specific users calendar
                try {

                    Calendar service = new Calendar.Builder(
                            GoogleNetHttpTransport.newTrustedTransport(),
                            GsonFactory.getDefaultInstance(),
                            credential
                    ).setApplicationName("CalSync").build();

                } catch (GeneralSecurityException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }


            return ResponseEntity.ok(Map.of(
                "message", "Login successful"
            ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        }
    }

    @PostMapping("/signup")
        public ResponseEntity<?> signup(@RequestBody UserRequest userRequest){
        String email = userRequest.getEmail();
        String password = userRequest.getPassword();

        System.out.println("Signup request received: " + userRequest.getEmail());

        MongoDBService.insertUser(email,password);



        return ResponseEntity.ok(Map.of(
                "message","Signup successful"
        ));
    }
}

