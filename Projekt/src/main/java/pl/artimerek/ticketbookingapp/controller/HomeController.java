package pl.artimerek.ticketbookingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.artimerek.ticketbookingapp.model.User;
import pl.artimerek.ticketbookingapp.service.TicketService;
import pl.artimerek.ticketbookingapp.service.UserService;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class HomeController {

    private final UserService userService;
    private final TicketService ticketService;
    private String currentLogged;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping
    public String getHome(Principal principal) {
        ticketService.setUser(principal.getName());
        currentLogged = principal.getName();
        return "home";
    }


    @GetMapping("/mytickets")
    public String getTickets(Model model) {
        User user = userService.getUserByEmail(currentLogged);
        model.addAttribute("tickets", user.getTickets());
        return "userTickets";
    }


}
