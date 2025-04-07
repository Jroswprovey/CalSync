// Alex 
//  building a login system where React 
// frontend sends email and password to Spring Boot 
// backend to validate the user and log them into the app

package org.calsync.Service;

public class LoginService {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

