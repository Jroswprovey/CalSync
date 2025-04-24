package org.calsync.Service;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.mongodb.client.model.Filters.eq;

public class MongoDBService {

    private static MongoClient mongoClient;


    private static MongoDatabase database;

    //Authorizes with mongoDB when class is initialized
    static {

        Properties configProps = new Properties();
        try (InputStream input = EmailService.class.getClassLoader().getResourceAsStream("mongoDBAuth.properties")){
            if (input != null){
                configProps.load(input);
            } else {
                System.err.println("mongoDBAuth.auth not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // url for the connection string
        String connectionString = configProps.getProperty("mongoDBAuth.auth", "");

        // Construct a ServerApi instance
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();


        // Create a new client and connect to the server
        mongoClient = MongoClients.create(settings);

            try {
                // Connect to the CalendarApp database in Atlas
                database = mongoClient.getDatabase("CalendarApp");

                // Send a ping to confirm a successful connection
                database.runCommand(new Document("ping", 1));
                System.out.println("Successfully connected to the CalendarApp database");

            } catch (MongoException e) {
                e.printStackTrace();
            }




    }



    public static void insertUser(String username, String password){

        // Use collection that is storing user info
        String collectionName = "Users";
        MongoCollection<Document> userInfo = database.getCollection(collectionName);

        // Check to see if username already exists in the database
        Document existingUser = userInfo.find(eq("username", username)).first();


        if (existingUser == null) {

            // If username doesn't already exist, add the new user details
            userInfo.insertOne(new Document()
                    .append("_id", new ObjectId())
                    .append("username", username.toLowerCase())
                    .append("password", password));

            System.out.println("User inserted successfully.");

        } else {

            // If username exists, do not add to the database
            System.out.println("Username already exists, please pick a different one");
        }

    }


    public static Document findUser(String username){

        String collectionName = "Users";
        MongoCollection<Document> userInfo = database.getCollection(collectionName);

        return userInfo.find(new Document("username", username)).first();

    }

    //returns ture or false value depending on whether user exists or not
    public static boolean userExist(String username){

        String collectionName = "Users";
        MongoCollection<Document> userInfo = database.getCollection(collectionName);

        Document user = userInfo.find(eq("username", username.toLowerCase())).first();

        return user != null;

    }



    public static String getPass(String username){

        // Use collection that is storing user info
        String collectionName = "Users";
        MongoCollection<Document> userInfo = database.getCollection(collectionName);

        //looks for username in DB
        Document foundUser = userInfo.find(new Document("username", username)).first();

        if (foundUser == null) {
            return null;
        }

        return foundUser.getString("password");

    }


}
