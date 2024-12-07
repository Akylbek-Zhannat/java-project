package com.example.lab4_vers2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/email")
public class AdminEmailController {

    @Autowired
    private EmailService emailService;


    @Autowired
    private UserRepository userRepository;
    @PostMapping("/send")

    public String sendEmailToUser(
            @RequestParam String username,
            @RequestParam String subject,
            @RequestParam String content
    ) {
        try {
            User user = userRepository.findByUsername(username).orElseThrow(() ->
                    new RuntimeException("Пользователь не найден!")
            );

            emailService.sendEmail(
                    "akylbekzhannat65@gmail.com",
                    user.getEmail(),
                    subject,
                    content
            );

            return "Сообщение отправлено пользователю " + username;
        } catch (Exception e) {
            System.err.println("Ошибка при отправке письма: " + e.getMessage());
            return "Не удалось отправить сообщение: " + e.getMessage();
        }
    }

}

