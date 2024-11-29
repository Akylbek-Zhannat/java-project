package com.example.lab4_vers2;

import ch.qos.logback.core.encoder.EchoEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String getAllUsers(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 3;
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<User> userPage = userService.getAllUsers(pageable);

        model.addAttribute("Users", userPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        return "user-list";
    }

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/Users";
    }



//    @GetMapping
//    public String getAllUsers(Model model) {
//        List<User> users = userService.getAllUsers();
//        model.addAttribute("tasks", users);
//        return "task-list";
//    }

//    @GetMapping("/register")
//    public String showRegistrationForm() {
//        return "register";
//    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors: " + bindingResult.getAllErrors());
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

          userService.registerUser(user);

//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);

        model.addAttribute("success", "Registration successful! Please login.");
        return "redirect:/login";
    }


    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @GetMapping("/{id}")
    public Optional<User> getUserId(@PathVariable String id) {
        return userRepository.findById(id);
    }


    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        Optional<User> userData = userRepository.findById(id);
        if (userData.isPresent()) {
            User user = userData.get();
            user.setUserName(updatedUser.getUserName());
            user.setEmail(updatedUser.getEmail());
            userRepository.save(user);
            return "User with id " + id + " updated";
        } else {
            return "User with id " + id + " not found ";
        }
    }


    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        Optional<User> userData = userRepository.findById(id);
        if (userData.isPresent()) {
            userRepository.deleteById(id);
            return "User with id " + id + " deleted";
        } else {
            return "User with id " + id + " not found";
        }
    }

}































//
//@Controller
//@RequestMapping("/Users")
//public class UserController {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @GetMapping
//    public String getAllUsers(@RequestParam(defaultValue = "0") int page, Model model) {
//        int pageSize = 3;
//        Pageable pageable = PageRequest.of(page, pageSize);
//
//        Page<User> userPage = userService.getAllUsers(pageable);
//
//        model.addAttribute("Users", userPage.getContent());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", userPage.getTotalPages());
//        return "user-list";
//    }
//
//    @GetMapping("/add")
//    public String showAddUserForm(Model model) {
//        model.addAttribute("user", new User());
//        return "user-add";
//    }
//
//    @PostMapping("/add")
//    public String addUser(@ModelAttribute User user) {
//        userService.createUser(user);
//        return "redirect:/Users";
//    }
//
//
//
//    @PostMapping("/register")
//    public String registerUser(@ModelAttribute User user, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            System.out.println("Validation errors: " + bindingResult.getAllErrors());
//            return "register";
//        }
//
//        if (userRepository.findByUsername(user.getUserName()).isPresent()) {
//            model.addAttribute("error", "Username already exists! Please choose another one.");
//            return "register";
//        }
//
//        if (!user.getPassword().equals(user.getPasswordConfirm())) {
//            model.addAttribute("error", "Passwords do not match. Please try again.");
//            return "register";
//        }
//
//        userService.registerUser(user);
//
////        user.setPassword(passwordEncoder.encode(user.getPassword()));
////        userRepository.save(user);
//
//        model.addAttribute("success", "Registration successful! Please login.");
//        return "redirect:/login";
//    }
//
//
//    @PostMapping("/create")
//    public User createUser(@RequestBody User user) {
//        return userRepository.save(user);
//    }
//
//    @GetMapping("/all")
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//
//    @GetMapping("/{id}")
//    public Optional<User> getUserId(@PathVariable String id) {
//        return userRepository.findById(id);
//    }
//
//
//    @PutMapping("/update/{id}")
//    public String updateUser(@PathVariable String id, @RequestBody User updatedUser) {
//        Optional<User> userData = userRepository.findById(id);
//        if (userData.isPresent()) {
//            User user = userData.get();
//            user.setUserName(updatedUser.getUserName());
//            user.setEmail(updatedUser.getEmail());
//            userRepository.save(user);
//            return "User with id " + id + " updated";
//        } else {
//            return "User with id " + id + " not found ";
//        }
//    }
//
//
//    @DeleteMapping("/delete/{id}")
//    public String deleteUser(@PathVariable String id) {
//        Optional<User> userData = userRepository.findById(id);
//        if (userData.isPresent()) {
//            userRepository.deleteById(id);
//            return "User with id " + id + " deleted";
//        } else {
//            return "User with id " + id + " not found";
//        }
//    }
//
//}
//
//
//
//
