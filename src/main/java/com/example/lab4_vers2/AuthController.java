package com.example.lab4_vers2;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/taskAll")
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "task-list";
    }

    @GetMapping("/taskUser")
    public String getUserTasks(
            @AuthenticationPrincipal org.springframework.security.core.userdetails.User principal,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        int pageSize = 3;

        User user = userRepository.findByUsername(principal.getUsername()).orElseThrow(() ->
                new RuntimeException("Пользователь не найден!")
        );

        Page<Task> taskPage;
        if (user.getRole() == User.Role.ADMIN) {
            taskPage = taskService.getAllTasks(PageRequest.of(page, pageSize));
        } else {
            taskPage = taskService.getTasksForUser(user.getId(), PageRequest.of(page, pageSize));
        }

        model.addAttribute("tasks", taskPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", taskPage.getTotalPages());

        return "task-list";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", User.Role.values());
        return "register";
    }


    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "success", required = false) String success, Model model) {
        if (success != null) {
            model.addAttribute("success", "Registration successful! Please log in.");
        }
        return "login";
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (userRepository.findByUsername(user.getUserName()).isPresent()) {
            model.addAttribute("error", "Username already exists! Please choose another one.");
            return "register";
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("error", "Passwords do not match. Please try again.");
            return "register";
        }
//        user.setUserName((user.getUserName()));
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.registerUser(user);

//        user.setPasswordConfirm(passwordEncoder.encode(user.getPasswordConfirm()));
//        userRepository.save(user);

        model.addAttribute("success", "Registration successful! Please login.");
        return "redirect:/login?success=true";
    }


}

