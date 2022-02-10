package pl.artimerek.ticketbookingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.artimerek.ticketbookingapp.model.Event;
import pl.artimerek.ticketbookingapp.model.Ticket;
import pl.artimerek.ticketbookingapp.model.User;
import pl.artimerek.ticketbookingapp.service.EventService;
import pl.artimerek.ticketbookingapp.service.TicketService;
import pl.artimerek.ticketbookingapp.service.UserService;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class HomeController {

    private final UserService userService;
    private final TicketService ticketService;
    private final EventService eventService;
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

    @GetMapping("/bootstrap")
    public String generateEvents() {
        Event event = new Event("Woodstock", "Cracow", LocalDate.now());

        for (int i = 1; i <= 5; i++) {
            Ticket ticket = new Ticket(i, BigDecimal.TEN);
            ticket.setEvent(event);
            ticketService.save(ticket);
        }

        eventService.save(event);


        return "home";
    }

    @GetMapping("/mytickets")
    public String getTickets(Model model) {
        User user = userService.getUserByEmail(currentLogged);
        model.addAttribute("tickets", user.getTickets());
        return "details";
    }


}
