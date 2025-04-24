/*
    Joseph Rosw-Provey
    3/26/2025

 */

package org.calsync;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class Main {
    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
    }
}