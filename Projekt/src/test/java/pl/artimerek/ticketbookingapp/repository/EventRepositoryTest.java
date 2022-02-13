package pl.artimerek.ticketbookingapp.repository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.artimerek.ticketbookingapp.model.Event;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
class EventRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EventRepository eventRepository;

    @BeforeEach
    void clear(){
        eventRepository.deleteAll();
    }

    @Test
    public void findByIdShouldReturnGoodObject() {
        // given
        Event event = new Event("Test", "Test", LocalDate.now());
        entityManager.persist(event);
        entityManager.flush();

        // when
        String actual = eventRepository.findById(event.getId()).get().getName();
        String expected = event.getName();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void saveTest() {
        // given
        Event event = new Event("Test", "Test", LocalDate.now());
        Event event1 = eventRepository.save(event);

        // when
        String actual = eventRepository.findById(event1.getId()).get().getName();
        String expected = event.getName();

        // then
        assertEquals(expected, actual);
    }

}