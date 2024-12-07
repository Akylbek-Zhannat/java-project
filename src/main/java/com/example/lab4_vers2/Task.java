package com.example.lab4_vers2;

import jakarta.persistence.EnumType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import jakarta.persistence.Enumerated;

@Document(collection = "Tasks")
public class Task {
    @Id
    private String id;
    private String name;
    private String title;
    private String description;

    private Task.Status status;

    public enum Status {
        IN_PROGRESS,
        COMPLETED
    }
    private String priority;
    private int userId;

    private LocalDateTime categoryCreationDate;




public Task(){}
    public Task(String id, String name, String title, String description, String priority, int userId) {
        this.id = id;
        this.name = name;
        this.title= title;
        this.description = description;
//        this.status = status;
        this.priority = priority;
        this.userId = userId;

        this.categoryCreationDate = LocalDateTime.now();
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getCateCreationDateAsDate() {
        return Date.from(categoryCreationDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    public void setCatetCreationDate(LocalDateTime categoryCreationDate) {
        this.categoryCreationDate = categoryCreationDate;
    }
}