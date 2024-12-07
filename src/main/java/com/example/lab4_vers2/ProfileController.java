package com.example.lab4_vers2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
@Controller
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Value("${upload.dir}")
    private String uploadDir;

    @PostMapping("/profile/upload")
    public String uploadProfilePicture(@RequestParam("file") MultipartFile file,
                                       @AuthenticationPrincipal org.springframework.security.core.userdetails.User principal,
                                       RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Файл не выбран!");
            return "redirect:/profile";
        }
        try {
            File uploadDirectory = new File(uploadDir);
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs();
            }

            String username = principal.getUsername();
            User user = userService.findByUsername(username).orElseThrow(() ->
                    new RuntimeException("Пользователь не найден!")
            );

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File targetFile = new File(uploadDir + File.separator + fileName);

            file.transferTo(targetFile);

            user.setProfilePicture(fileName);
            userService.saveUser(user);
            System.out.println("Файл сохранён в: " + targetFile.getAbsolutePath());
            System.out.println("Пользователь: " + user.getUserName());
            System.out.println("Фото сохранено в объекте user: " + user.getProfilePicture());

            redirectAttributes.addFlashAttribute("message", "Фото успешно загружено!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "Ошибка загрузки файла: " + e.getMessage());
        }

        return "redirect:/profile";
    }


    @PostMapping("/profileAdmin/upload")
    public String uploadProfileAdminPicture(@RequestParam("file") MultipartFile file,
                                       @AuthenticationPrincipal org.springframework.security.core.userdetails.User principal,
                                       RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Файл не выбран!");
            return "redirect:/profile";
        }
        try {
            File uploadDirectory = new File(uploadDir);
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs();
            }

            String username = principal.getUsername();
            User user = userService.findByUsername(username).orElseThrow(() ->
                    new RuntimeException("Пользователь не найден!")
            );

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File targetFile = new File(uploadDir + File.separator + fileName);

            file.transferTo(targetFile);

            user.setProfilePicture(fileName);
            userService.saveUser(user);
            System.out.println("Файл сохранён в: " + targetFile.getAbsolutePath());
            System.out.println("Пользователь: " + user.getUserName());
            System.out.println("Фото сохранено в объекте user: " + user.getProfilePicture());

            redirectAttributes.addFlashAttribute("message", "Фото успешно загружено!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "Ошибка загрузки файла: " + e.getMessage());
        }

        return "redirect:/profileAdmin";
    }


    @GetMapping("/profileAdmin")
    public String showProfilePageAdmin(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            model.addAttribute("error", "User not found!");
        }
        return "profileAdmin";

    }
    @GetMapping("/profile")
    public String showProfilePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            model.addAttribute("error", "User not found!");
        }
        return "profile";

    }


    @GetMapping("/profile/edit")
    public String showEditProfilePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            model.addAttribute("username", user.getUserName());
            model.addAttribute("email", user.getEmail());
        }

        return "edit-profile";
    }

    @PostMapping("/profile/edit")
    public String updateProfile(@ModelAttribute User user, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userRepository.findByUsername(currentUsername).orElse(null);

        if (currentUser != null) {
            if (user.getUserName() != null && !user.getUserName().isEmpty()) {
                currentUser.setUserName(user.getUserName());
            }
            if (user.getEmail() != null && !user.getEmail().isEmpty()) {
                currentUser.setEmail(user.getEmail());
            }
            System.out.println("Старый пароль: " + currentUser.getPassword());
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
                System.out.println("Новый пароль: " + currentUser.getPassword());
            } else {
                System.out.println("Пароль не изменён");
            }

            userRepository.save(currentUser);
            model.addAttribute("success", "Профиль успешно обновлён!");
        } else {
            model.addAttribute("error", "Пользователь не найден!");
        }

        return "profile";
    }

}













