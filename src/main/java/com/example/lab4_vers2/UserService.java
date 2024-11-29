package com.example.lab4_vers2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    private int getNextUserId() {
        User lastUser = userRepository.findTopByOrderByIdDesc();
        if (lastUser != null) {
            return lastUser.getId() + 1;
        } else {
            return 1;
        }
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }
    public void registerUser(User user) {
        int nextId = getNextUserId();
        user.setId(nextId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void saveUser(String username, String password) {
        User user = new User();
        user.setUserName(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
}



































//
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public Page<User> getAllUsers(Pageable pageable) {
//        return userRepository.findAll(pageable);
//    }
//
//    private int getNextUserId() {
//        User lastUser = userRepository.findTopByOrderByIdDesc();
//        if (lastUser != null) {
//            return lastUser.getId() + 1;
//        } else {
//            return 1;
//        }
//    }
//    public User createUser(User user) {
//        return userRepository.save(user);
//    }
//    public void registerUser(User user) {
//        int nextId = getNextUserId();
//        user.setId(nextId);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//    }
//
//
//    public Optional<User> findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
//    public User getUserByUsername(String username) {
//        return userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }
//
//    public void saveUser(String username, String password) {
//        User user = new User();
//        user.setUserName(username);
//        user.setPassword(passwordEncoder.encode(password));
//        userRepository.save(user);
//    }
//}
