package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;
import java.util.List;

@Controller
@RequestMapping("/")
public class AdminController {

    private final UserServiceImp userService;
    private final RoleServiceImp roleService;

    @Autowired
    public AdminController(UserServiceImp userService, RoleServiceImp roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/admin/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "/admin/users";
    }

    @GetMapping("/admin/user-create")
    public String createUserForm(@ModelAttribute("user") User user){
        return "/admin/user-create";
    }

    @PostMapping("/admin/user-create")
    public String createUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        List<Role> listRoles = roleService.findAllRole();
        model.addAttribute("listRoles",listRoles);
        model.addAttribute("user", user);
        return "/admin/user-update";
    }

    @PostMapping("/admin/user-update")
    public String updateUser(User user){
        userService.saveUser(user);
        return "redirect:/admin/users";
    }
}
