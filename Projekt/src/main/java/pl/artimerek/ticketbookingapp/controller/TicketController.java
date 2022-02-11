package pl.artimerek.ticketbookingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.artimerek.ticketbookingapp.model.Event;
import pl.artimerek.ticketbookingapp.model.Ticket;
import pl.artimerek.ticketbookingapp.service.TicketService;
import pl.artimerek.ticketbookingapp.service.UserService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final UserService userService;

    @GetMapping
    public String getTickets(Model model){
        model.addAttribute("tickets", ticketService.findAllFreeTickets());
        return "tickets";
    }

    @GetMapping("/ticket/{id}")
    public String getTicket(@PathVariable Long id){
        Ticket ticket = ticketService.getById(id);
        String name = ticketService.getUser();
        ticket.setUser(userService.getUserByEmail(name));
        ticketService.save(ticket);

        return "successBooked";
    }


}
