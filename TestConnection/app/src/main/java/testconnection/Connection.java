package testconnection;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Demo Class 
 * Tests the connection between the backend and MongoDB(Atlas)
 * Implements the feature to allow users to sign up or log in 
 */

public class Connection {

    /**
     * Adds new user account in the database during sign up
     * 
     * @param database - the database storing user info
     * @param username - username for the new account
     * @param password - password for the new account
     */

    public static void insertUser(MongoDatabase database, String username, String password){
        
        // Use collection that is storing user info
        String collectionName = "Users";
        MongoCollection<Document> userInfo = database.getCollection(collectionName);
        
        // Check to see if username already exists in the database
        Document existingUser = userInfo.find(eq("username", username)).first();


        if (existingUser == null) {

            // If username doesn't already exist, add the new user details
            userInfo.insertOne(new Document()
                .append("_id", new ObjectId())
                .append("username", username)
                .append("password", password));

            System.out.println("User inserted successfully.");

        } else {

            // If username exists, do not add to the database
            System.out.println("Username already exists, please pick a different one");
        }
        
    }

    /**
     * Check if user account exists if user tries to log in 
     * 
     * @param database - database storing user info
     * @param username - username to be checked
     */

    public static void findUser(MongoDatabase database, String username){

        // Use collection that is storing user info
        String collectionName = "Users";
        MongoCollection<Document> userInfo = database.getCollection(collectionName);

        // Check to see if username already exists in the database
        Document existingUser = userInfo.find(new Document("username", username)).first();

        
        if (existingUser == null) {

            // If username not found, do not allow the user to log in
            System.out.println("User not found, please sign up");

        } else {

            // If username found, allow the user to log in and access the dashboard
            System.out.println("User " + username + " found in the database");
        }

    }


    // public static void readDocument(MongoDatabase database) {
    //     MongoCollection<Document> userInfo = database.getCollection("Users");

    //     Document user= collection.find(eq("username", "user1" )).first();
    // }

    public static void main(String[] args) {

        // uri for the connection string
        String connectionString = "mongodb+srv://sshah6:db_password@calendarappcluster.tilrgx6.mongodb.net/?retryWrites=true&w=majority&appName=CalendarAppCluster";

        // Construct a ServerApi instance    
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();


        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {

            try {

                // Connect to the CalendarApp database in Atlas
                MongoDatabase database = mongoClient.getDatabase("CalendarApp");

                // Send a ping to confirm a succesful connection
                database.runCommand(new Document("ping", 1));
                System.out.println("Successfully connected to the CalendarApp database");

                // New user's username and password obtained during sign-up
                String newUsername = "user_temp";
                String newPassword = "password_temp1";

                // Demo 
                insertUser(database, newUsername, newPassword);
                findUser(database, newUsername);

            } catch (MongoException e) {
                e.printStackTrace();
            }
        }

    }
}