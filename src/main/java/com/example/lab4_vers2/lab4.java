package com.example.lab4_vers2;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class lab4 {
    private MongoCollection<Document> userCollection;
    private MongoCollection<Document> taskCollection;
    private MongoCollection<Document> categoryCollection;

    public lab4() {
        MongoDatabase database = Connect.connect();
        userCollection = database.getCollection("Users");
        taskCollection = database.getCollection("Tasks");
        categoryCollection = database.getCollection("Categories");
    }

    public void createUser(User user) {
        try {
//            String creationDate = user.getAccountCreationDate().format(DateTimeFormatter.ISO_DATE_TIME);
            Document doc = new Document("_id", user.getId())
                    .append("username", user.getUserName())
                    .append("email", user.getEmail())
                    .append("password", user.getPassword())
                    .append("passwordConfirm", user.getPasswordConfirm())
                    .append("account_creation_date", user.getAccountCreationDateAsDate());
//                    .append("account_creation_date", creationDate);
            userCollection.insertOne(doc);
            System.out.println("User created!! ");
        } catch (Exception e) {
            System.out.println("Error creating user " + e.getMessage());
        }
    }

    public void createTask(Task task) {
        try {
            Document doc = new Document("_id", task.getId())
                    .append("title", task.getName())
                    .append("description", task.getDescription())
                    .append("status", task.getStatus())
                    .append("priority", task.getPriority())
                    .append("user_id", task.getUserId())
//                    .append("category_id", task.getCategoryId())
                    .append("account_creation_date", task.getCateCreationDateAsDate());
            taskCollection.insertOne(doc);
            System.out.println("Task created!! ");
        } catch (Exception e) {
            System.out.println("Error creating Task " + e.getMessage());
        }
    }

    public void createCategory(Category category) {
        try {
            Document doc = new Document("_id", category.getId())
                    .append("name", category.getName());
            categoryCollection.insertOne(doc);
            System.out.println("Category created!! ");
        } catch (Exception e) {
            System.out.println("Error creating Task " + e.getMessage());
        }
    }

    public void readUser(String id) {
        try {
            Document query = new Document("_id", id);
            Document userDoc = userCollection.find(query).first();
            if (userDoc != null){
                System.out.println("User found " + userDoc.toJson());
            }
            else{
                System.out.println("User not found ");
            }
        } catch (Exception e) {
            System.out.println("Error reading user " + e.getMessage());
        }
    }


    public void updateUser(String id, String newName, String newEmail, String newPassword) {
        try {
            Document query = new Document("_id", id);
            Document userDoc = userCollection.find(query).first();
            if (userDoc != null) {
                Document update = new Document("$set", new Document("name", newName).append("email", newEmail).append("password", newPassword));
                userCollection.updateOne(query, update);
                System.out.println("User updated ");
            } else {
                System.out.println("User not found ");
            }
        } catch (Exception e) {
            System.out.println("Error updating user " + e.getMessage());
        }
    }


    public void deleteUser(String id) {
        try {
            Document query = new Document("_id", id);
            long deletedCount = userCollection.deleteOne(query).getDeletedCount();
            if (deletedCount > 0) {
                System.out.println("User deleted ");
            } else {
                System.out.println("User not found");
            }
        } catch (Exception e) {
            System.out.println("Error deleting user " + e.getMessage());
        }
    }

}
