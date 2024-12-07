package com.example.lab4_vers2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByTitleContaining(String title);

    Page<Task> findByStatus(Task.Status status, Pageable pageable);
    Page<Task> findByUserId(int userId, Pageable pageable);

    Page<Task> findAll(Pageable pageable);

}
