package pl.artimerek.ticketbookingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.artimerek.ticketbookingapp.model.Event;
import pl.artimerek.ticketbookingapp.service.EventService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @GetMapping
    public String getEvents(Model model){
        model.addAttribute("events", eventService.findAll());
        return "events";
    }

    //  TODO FORM FOR CREATING EVENTS

//    @GetMapping("/create-form")
//    public String teamSubmit(Model model) {
//        model.addAttribute("event", new Event());
//        return "createEvent";
//    }
//
//    @PostMapping("/create-form")
//    public String teamForm(@ModelAttribute Event event, Model model) {
//
//        model.addAttribute("event", event);
//        eventService.save(event);
//        return "result";
//    }
}
