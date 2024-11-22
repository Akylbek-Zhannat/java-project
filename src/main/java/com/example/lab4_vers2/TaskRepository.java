package com.example.lab4_vers2;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByTitleContaining(String title);
    List<Task> findByUserId(int userId);
    List<Task> findByStatus(String status);
}
