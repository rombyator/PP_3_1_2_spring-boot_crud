package dev.curly.simplecrud.controller;

import dev.curly.simplecrud.model.User;
import dev.curly.simplecrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userRepo.findAll());

        return "user/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("user", new User());

        return "user/create";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("user") User user, Model model) {
        userRepo.save(user);
        model.addAttribute("user", user);

        return "user/show";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        var user = userRepo
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
        model.addAttribute("user", user);

        return "user/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        var user = userRepo
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
        model.addAttribute("user", user);

        return "user/edit";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") long id, @ModelAttribute("user") User user, Model model) {
        user.setId(id);
        userRepo.save(user);
        model.addAttribute("user", user);

        return "user/show";
    }
}
