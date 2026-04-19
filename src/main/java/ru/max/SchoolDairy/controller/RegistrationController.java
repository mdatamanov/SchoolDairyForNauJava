package ru.max.SchoolDairy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.max.SchoolDairy.model.User;
import ru.max.SchoolDairy.service.UserService;
import ru.max.SchoolDairy.service.UserServiceImpl;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        try {
            userService.createUser(user);
            return "redirect:/login?registered=true";
        } catch (Exception ex) {
            model.addAttribute("message", "Пользователь с таким именем уже существует");
            return "registration";
        }
    }
}
