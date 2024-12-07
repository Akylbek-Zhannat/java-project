package com.example.lab4_vers2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        System.out.println("User found: " + user.getUserName());
        System.out.println("Role: " + user.getRole());

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }

























//
//    @Service
//    public class CustomUserDetailsService implements UserDetailsService {
//        private static final Logger logger = LoggerFactory.getLogger(com.example.lab4_vers2.CustomUserDetailsService.class);
//
//        @Autowired
//        private UserRepository userRepository;
//
//        public CustomUserDetailsService(UserRepository userRepository) {
//            this.userRepository = userRepository;
//        }
//        @Override
//        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//            User user = userRepository.findByUsername(username)
//                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//            System.out.println("User found: " + user.getUserName());
//            System.out.println("Role: " + user.getRole());
//
//            return org.springframework.security.core.userdetails.User.builder()
//                    .username(user.getUserName())
//                    .password(user.getPassword())
//                    .roles(user.getRole().name())
//                    .build();
//        }









//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        System.out.println("User found: " + user.getUserName());
//
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(user.getUserName())
//                .password(user.getPassword())
//                .roles((user.getRole().name())
//                .build();
//    }
}

