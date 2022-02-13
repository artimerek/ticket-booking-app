package pl.artimerek.ticketbookingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.artimerek.ticketbookingapp.model.Event;
import pl.artimerek.ticketbookingapp.repository.EventRepository;
import pl.artimerek.ticketbookingapp.service.EventService;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @Override
    public Event getRandomEvent(){
        String name = generateRandomString();
        String place = generateRandomString();
        LocalDate date = getRandomDate();

        return new Event(name, place, date);
    }

    private String generateRandomString(){
         String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
         SecureRandom rnd = new SecureRandom();

        Random random = new Random();
        int size = random.ints(4, 13)
                .findFirst()
                .getAsInt();
        return IntStream.range(0, size)
                .mapToObj(i -> String.valueOf(AB.charAt(rnd.nextInt(AB.length()))))
                .collect(Collectors.joining());

    }

    private LocalDate getRandomDate(){
        LocalDate stop = LocalDate.of(2030, 12, 31);
        long days = ChronoUnit.DAYS.between(LocalDate.now(), stop);
        return LocalDate.now().plusDays(new Random().nextInt((int) days + 1));
    }
}
