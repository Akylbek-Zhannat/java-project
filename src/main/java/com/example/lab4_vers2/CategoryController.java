package com.example.lab4_vers2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categorys")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public String getAllCategory(Model model) {
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        return "category-list";
    }


    @GetMapping("/search")
    public String searchCategory(@RequestParam("name") String name, Model model) {
        List<Category> categories = categoryService.searchCategory(name);
        model.addAttribute("tasks", categories);
        model.addAttribute("searchQuery", name);
        return "category-list";
    }



    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category, BindingResult bindingResult, Model model) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("categories", categoryRepository.findAll());
                return "category-list";
            }
            categoryRepository.save(category);
            return "redirect:/categorys";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Некорректное значение статуса задачи.");
            return "category-list";
        }
    }




    @GetMapping("/edit/{id}")
    public String showEditCategoryForm(@PathVariable("id") String id, Model model) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "category-edit";
        }
        return "redirect:/categorys";
    }

    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable("id") String id, @ModelAttribute Category category) {
        try {
            categoryService.updateCategory(id, category);
        } catch (Exception e) {
            e.printStackTrace();
            return "category-edit";
        }
        return "redirect:/categorys";
    }


    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") String id) {
        categoryService.deleteCategory(id);
        return "redirect:/categorys";
    }
}

