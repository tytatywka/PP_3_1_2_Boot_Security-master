package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.security.Principal;

@Controller
public class UserController {

    private final UserServiceImp service;
    @Autowired
    public UserController(UserServiceImp service) {
        this.service = service;
    }

    @GetMapping("/user/userid")
    public String userPage(Principal principal, Model model) {
        model.addAttribute("user", service.findByName(principal.getName()));
        return "/user/userid";
    }
}
