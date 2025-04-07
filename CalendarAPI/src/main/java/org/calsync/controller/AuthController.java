
// Alex 
// Accepts a POST /api/auth/login request
// Reads email + password from the frontend
// Validates credentials (mock logic for now)

// To do: Start Spring Boot backend
//  so React (front end) login page can send the email and password to
// the backend to log the user in.

package org.calsync.controller;

import org.calsync.Service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // allow React frontend
public class AuthController {

    @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody LoginService loginService) {
        String email = loginService.getUsername();
        String password = loginService.getPassword();

        // TEMP: Replace this with real database check later
        if (email.equalsIgnoreCase("test@email.com") && password.equals("1234")) {
            return ResponseEntity.ok(Map.of(
                "message", "Login successful",
                "token", "fake-jwt-token-123"
            ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        }
    }
}

