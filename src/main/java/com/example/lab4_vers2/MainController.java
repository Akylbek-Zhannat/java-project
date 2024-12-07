package com.example.lab4_vers2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MainController {

    @GetMapping("/default")
    public String defaultPage(Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/mainAdmin";
        } else if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {
            return "redirect:/main";
        }
        return "redirect:/login";
    }

    @GetMapping("/mainAdmin")
    public String mainAdmin() {
        return "mainAdmin";
    }

    @GetMapping("/main")
    public String mainUser() {
        return "main";
    }
}







