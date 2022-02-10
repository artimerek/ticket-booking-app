package pl.artimerek.ticketbookingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.artimerek.ticketbookingapp.model.User;
import pl.artimerek.ticketbookingapp.service.UserService;


@RequiredArgsConstructor
@Controller
@RequestMapping("/register")
public class UserRegisterController {

    private final UserService userService;

    @GetMapping
    public String showRegisterForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String register(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/register?success";
    }
}
