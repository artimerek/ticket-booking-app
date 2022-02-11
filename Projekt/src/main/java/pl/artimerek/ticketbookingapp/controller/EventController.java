package pl.artimerek.ticketbookingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.artimerek.ticketbookingapp.model.Event;
import pl.artimerek.ticketbookingapp.model.EventHelper;
import pl.artimerek.ticketbookingapp.model.Ticket;
import pl.artimerek.ticketbookingapp.service.EventService;
import pl.artimerek.ticketbookingapp.service.TicketService;

import java.time.LocalDate;
import java.util.Set;

@RequiredArgsConstructor
@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final TicketService ticketService;

    @GetMapping
    public String getEvents(Model model){
        model.addAttribute("events", eventService.findAll());
        return "events";
    }

    @GetMapping("/event/{eventId}")
    public String getEventById(@PathVariable Long eventId, Model model){
        Event event = eventService.findById(eventId);
        model.addAttribute("tickets", event.getTickets() );
        model.addAttribute("event", event);

        return "eventTickets";

    }

    @GetMapping("/generateEvent")
    public String generateEvents(Model model) {
        Event event = eventService.getRandomEvent();
        Set<Ticket> ticketSet = ticketService.generateTickets(event, 5);

        event.setTickets(ticketSet);
        eventService.save(event);

        model.addAttribute("event", event);

        return "eventCreated";
    }


    @GetMapping("/create-form")
    public String eventSubmit(Model model) {
        model.addAttribute("eventHelper", new EventHelper());
        return "createEvent";
    }

    @PostMapping("/create-form")
    public String eventForm(@ModelAttribute EventHelper eventHelper, Model model) {

        Event event = new Event(eventHelper.getName(),
                eventHelper.getPlaceName(),
                LocalDate.parse(eventHelper.getDate()));


        Set<Ticket> ticketSet = ticketService.generateTickets(event, eventHelper.getTicketsAmount());

        event.setTickets(ticketSet);
        eventService.save(event);

        model.addAttribute("event", event);

        return "eventCreated";
    }
}
