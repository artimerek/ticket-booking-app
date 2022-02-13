package pl.artimerek.ticketbookingapp.service.impl;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.artimerek.ticketbookingapp.model.Ticket;
import pl.artimerek.ticketbookingapp.repository.TicketRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    private Ticket ticket;
    private Ticket ticket1;

    List<Ticket> tickets;

    @BeforeEach
    public void setUp() {
        tickets = new ArrayList<>();
        ticket = new Ticket(1, BigDecimal.TEN);
        ticket1 = new Ticket(2, BigDecimal.TEN);
        tickets.add(ticket);
        tickets.add(ticket1);
    }
    @AfterEach
    public void tearDown() {
        ticket = ticket1 = null;
        tickets = null;
    }


    @Test
    public void givenIdThenShouldReturnTicketOfThatId() {
        //  given
        ticket.setId(1L);

        int expected = 1;

        Mockito.when(ticketRepository.findById(1L))
                .thenReturn(Optional.of(ticket));

        //  when
        int actual = ticketService.findById(1L).getSeatNumber();


        //  then
        assertEquals(expected, actual);

    }

    @Test
    public void givenIdThenShouldThrowExceptionIfUserWithGivenIdNotFounded() {

        Mockito.when(ticketRepository.findById(3L)).thenReturn(Optional.empty());

        //  when + then
        assertThrows(IllegalArgumentException.class,
                () -> ticketService.findById(3L));

    }

   @Test
    public void findAllShouldReturnListWithGoodSize(){

        int expected = 2;

       Mockito.when(ticketRepository.findAll()).thenReturn(tickets);

       //   when
       int actual = ticketService.findAll().size();

       //   then
       assertEquals(expected, actual);
   }

}