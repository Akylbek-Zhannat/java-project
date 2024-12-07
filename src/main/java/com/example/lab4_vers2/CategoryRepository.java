package com.example.lab4_vers2;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
public interface CategoryRepository extends MongoRepository<Category, String> {
    List<Category> findByName(String name);


}
