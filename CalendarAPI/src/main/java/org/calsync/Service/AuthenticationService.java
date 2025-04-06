package org.calsync.Service;

public class AuthenticationService {


    //Basic POC for Authentication with front end
    public static boolean Authenticate(String Username, String Password){
        if (Username.equalsIgnoreCase("test") && Password.equals("test") ){

            return true;

        }else {
            return false;
        }
    }
}
