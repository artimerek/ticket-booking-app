package pl.artimerek.ticketbookingapp.service;

import pl.artimerek.ticketbookingapp.model.Event;

import java.util.List;

public interface EventService {

    List<Event> findAll();

    Event getById(Long eventId);

    void save(Event event);

    Event findById(Long eventId);

    Event getRandomEvent();

    void deleteById(Long eventId);
}
