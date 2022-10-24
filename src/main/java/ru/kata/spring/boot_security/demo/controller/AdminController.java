package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/read")
    public String showAllUsers(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "read";
    }

    @GetMapping("/{id}")
    public String showUserById(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("user", userService.findUserById(id));
        return "user";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "create";
    }

    @PostMapping()
    public String save(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/read";
    }

    @GetMapping("/{id}/update")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "update";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") long id) {
        userService.updateUser(id, user);
        return "redirect:/admin/read";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/read";
    }
}