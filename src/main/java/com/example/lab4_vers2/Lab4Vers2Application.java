package com.example.lab4_vers2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.net.InetAddress;

@SpringBootApplication
public class Lab4Vers2Application {
    public static void main(String[] args) {
        SpringApplication.run(Lab4Vers2Application.class, args);

//
//        lab4 users = new lab4();
//        User user = new User(2, "Rakym", "Rakym@email.com", "123", "123");
//        User user2 = new User(3, "Aruzhan", "Aru@gmail.com", "123", "123");
//        users.createUser(user);
//        users.createUser(user2);
//        users.readUser("4");
//        users.updateUser("3", "Nurbol", "Nur@iitu.com", "1234");
//        users.deleteUser("3");
//
//        lab4 tasks = new lab4();
//        Task task1 = new Task(2, "Finish lab", "finish task", "in prose","High", 1, 1);
//        Task task2 = new Task(3, "finish SRS", "send file", "not start", "Low",2, 1);
//        tasks.createTask(task1);
//        tasks.createTask(task2);
//
//        lab4 categories = new lab4();
//        Category category1 = new Category(2, "University");
//        Category category2 = new Category(3, "Personal");
//        categories.createCategory(category1);
//        categories.createCategory(category2);

    }
//
//    @RestController
//    @RequestMapping("/Users")
//    public class UserController {
//        @Autowired
//        private UserRepository userRepository;
//
//        @Autowired
//        private TaskRepository taskRepository;
//
//        @PostMapping("/create")
//        public User createUser(@RequestBody User user) {
//            return userRepository.save(user);
//        }
//
//        @GetMapping("/all")
//        public List<User> getAllUsers() {
//            return userRepository.findAll();
//        }
//
//
//        @GetMapping("/{id}")
//        public Optional<User> getUserId(@PathVariable String id) {
//            return userRepository.findById(id);
//        }
//
//
//        @PutMapping("/update/{id}")
//        public String updateUser(@PathVariable String id, @RequestBody User updatedUser) {
//            Optional<User> userData = userRepository.findById(id);
//            if (userData.isPresent()) {
//                User user = userData.get();
//                user.setName(updatedUser.getName());
//                user.setEmail(updatedUser.getEmail());
//                userRepository.save(user);
//                return "User with id " + id + " updated";
//            } else {
//                return "User with id " + id + " not found ";
//            }
//        }
//
//
//        @DeleteMapping("/delete/{id}")
//        public String deleteUser(@PathVariable String id) {
//            Optional<User> userData = userRepository.findById(id);
//            if (userData.isPresent()) {
//                userRepository.deleteById(id);
//                return "User with id " + id + " deleted";
//            } else {
//                return "User with id " + id + " not found";
//            }
//        }
//    }



}