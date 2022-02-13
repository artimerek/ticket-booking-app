package pl.artimerek.ticketbookingapp.service.impl;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.artimerek.ticketbookingapp.model.Event;
import pl.artimerek.ticketbookingapp.repository.EventRepository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    private Event event;
    private Event event1;

    List<Event> events;

    @BeforeEach
    public void setUp() {
        events = new ArrayList<>();
        event = new Event("Test", "TestCity", LocalDate.now());
        event1 = new Event("Test1", "TestCity1", LocalDate.now());
        events.add(event);
        events.add(event1);
    }

    @AfterEach
    public void tearDown() {
        event = event1 = null;
        events = null;
    }


    @Test
    public void givenIdThenShouldReturnEventOfThatId() {
        //  given
        event.setId(1L);

        String expected = "Test";

        Mockito.when(eventRepository.findById(1L))
                .thenReturn(Optional.of(event));

        //  when
        String actual = eventService.findById(1L).getName();


        //  then
        assertEquals(expected, actual);

    }

    @Test
    public void givenIdThenShouldThrowExceptionIfEventWithGivenIdNotFounded() {

        Mockito.when(eventRepository.findById(3L)).thenReturn(Optional.empty());

        //  when + then
        assertThrows(IllegalArgumentException.class,
                () -> eventService.findById(3L));

    }

    @Test
    public void findAllShouldReturnListWithGoodSize() {

        int expected = 2;

        Mockito.when(eventRepository.findAll()).thenReturn(events);

        //   when
        int actual = eventService.findAll().size();

        //   then
        assertEquals(expected, actual);
    }

}