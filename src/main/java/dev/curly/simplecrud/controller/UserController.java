package dev.curly.simplecrud.controller;

import dev.curly.simplecrud.model.User;
import dev.curly.simplecrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.getAll());

        return "user/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("user", new User());

        return "user/create";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("user") User user, Model model) {
        userService.update(user);
        model.addAttribute("user", user);

        return "user/show";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        var user = userService.getById(id);
        model.addAttribute("user", user);

        return "user/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        var user = userService.getById(id);
        model.addAttribute("user", user);

        return "user/edit";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") long id, @ModelAttribute("user") User user, Model model) {
        user.setId(id);
        userService.update(user);
        model.addAttribute("user", user);

        return "user/show";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);

        return "redirect:/";
    }
}
