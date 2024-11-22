package com.example.lab4_vers2;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Connect {
    private static MongoDatabase database;
    private static MongoClient mongoClient;

    public static MongoDatabase connect() {
            try {
                String uri = "mongodb://localhost:27017";
                mongoClient = MongoClients.create(uri);
                database = mongoClient.getDatabase("Zhannat_Lab4");
                System.out.println("Connected to MongoDB successfully");
            } catch (Exception e) {
                System.out.println("Error connecting to MongoDB " + e.getMessage());
            }
        return database;
    }












//    public static void closeConnection() {
//        if (mongoClient != null) {
//            mongoClient.close();
//            System.out.println("MongoDB connection closed.");
//        }
//    }
}
