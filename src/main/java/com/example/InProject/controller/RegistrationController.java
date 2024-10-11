package com.example.InProject.controller;
import com.example.InProject.model.RoleEnum;
import com.example.InProject.model.UserModel;
import com.example.InProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registrationView() {
        return "regis";
    }

   /*@PostMapping("/registration")
    public String registrationUser(UserModel user, Model model) {
        if (userRepository.existsByUsername(user.getUsername())) {
            model.addAttribute("message", "Пользователь уже существует");
            return "regis";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(RoleEnum.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login";
    }*/
   @PostMapping("/registration")
   public String registrationUser(UserModel user, Model model) {
       try {
           if (userRepository.existsByUsername(user.getUsername())) {
               model.addAttribute("message", "Пользователь уже существует");
               return "regis";
           }

           String password = user.getPassword();
           if (!isValidPassword(password)) {
               model.addAttribute("message", "Пароль должен содержать не менее 6 символов, включая хотя бы одну цифру и одну заглавную букву");
               return "regis";
           }

           user.setActive(true);
           user.setRoles(Collections.singleton(RoleEnum.USER));
           user.setPassword(passwordEncoder.encode(user.getPassword()));
           userRepository.save(user);

           return "redirect:/login";
       } catch (Exception e) {

           model.addAttribute("message", "Произошла ошибка при сохранении пользователя: " + e.getMessage());
           return "regis";
       }
   }


    private boolean isValidPassword(String password) {
        if (password.length() < 6) {
            return false;
        }
        boolean hasDigit = false;
        boolean hasUpperCase = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            }
        }
        return hasDigit && hasUpperCase;
    }
}

