package pl.artimerek.ticketbookingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.artimerek.ticketbookingapp.model.Event;
import pl.artimerek.ticketbookingapp.repository.EventRepository;
import pl.artimerek.ticketbookingapp.service.EventService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event getById(Long eventId) {
        return eventRepository.getById(eventId);
    }

    @Override
    public void save(Event event) {
        eventRepository.save(event);
    }

    @Override
    public Event findById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void deleteById(Long eventId) {
        eventRepository.deleteById(eventId);
    }
}
