// Alex 
//  building a login system where React 
// frontend sends email and password to Spring Boot 
// backend to validate the user and log them into the app

package org.calsync.DTO;



public class LoginRequest {
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

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

