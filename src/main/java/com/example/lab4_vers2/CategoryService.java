//package com.example.lab4_vers2;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class CategoryService {
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//    public List<Category> getAllCategories() {
//        return categoryRepository.findAll();
//    }
//    public Optional<Category> getCategoryById(String categoryId) {
//        return categoryRepository.findById(categoryId);
//    }
//
//    public Category addCategory(Category category) {
//        if (categoryRepository.findByName(category.getName()).isPresent()) {
//            throw new IllegalArgumentException("Category name already exists");
//        }
//        return categoryRepository.save(category);
//    }
//    public void deleteCategory(String categoryId) {
//        categoryRepository.deleteById(categoryId);
//    }
//}
