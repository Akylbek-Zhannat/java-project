package com.example.lab4_vers2;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
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
        userService.registerUser(user);  // Регистрируем пользователя через сервис

//        user.setPasswordConfirm(passwordEncoder.encode(user.getPasswordConfirm()));
//        userRepository.save(user);

        model.addAttribute("success", "Registration successful! Please login.");
        return "redirect:/login";
    }


}
