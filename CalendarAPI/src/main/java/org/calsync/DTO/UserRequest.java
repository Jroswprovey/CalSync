// Alex 
//  building a login system where React 
// frontend sends email and password to Spring Boot 
// backend to validate the user and log them into the app

package org.calsync.DTO;


public class UserRequest {
    private String email;
    private String password;
    private String accessToken;


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAccessToken() {
        return accessToken;
    }

}

