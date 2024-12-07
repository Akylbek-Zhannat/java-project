package com.example.lab4_vers2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public Page<Task> getTasksForUser(int userId, Pageable pageable) {
        return taskRepository.findByUserId(userId, pageable);
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public Page<Task> filterTasksByStatus(Task.Status status, Pageable pageable) {
        return taskRepository.findByStatus(status, pageable);
    }
    public long countAllTasks() {
        return taskRepository.count();
    }
    public List<Task> searchTasks(String title) {
        return taskRepository.findByTitleContaining(title);
    }
//    public List<Task> filterTasksByStatus(String status) {
//        return taskRepository.findByStatus(status);
////          return taskRepository.findByStatus(status).orElse(Collections.emptyList());
//
//    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(String id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setPriority(taskDetails.getPriority());
        return taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }
}




