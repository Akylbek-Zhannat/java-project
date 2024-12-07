package com.example.lab4_vers2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

        public List<Category> getAllCategory() {
            return categoryRepository.findAll();
    }

    public long countAllCategory() {
        return categoryRepository.count();
    }
    public List<Category> searchCategory(String name) {
        return categoryRepository.findByName(name);
    }


    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> getCategoryById(String id) {
        return categoryRepository.findById(id);
    }

    public Category updateCategory(String id, Category categoryDetails) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Категория с ID " + id + " не найдена"));
        category.setName(categoryDetails.getName());
        return categoryRepository.save(category);
    }


    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }
}




