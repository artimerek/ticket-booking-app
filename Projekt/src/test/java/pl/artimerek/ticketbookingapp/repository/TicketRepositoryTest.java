package pl.artimerek.ticketbookingapp.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.artimerek.ticketbookingapp.model.Ticket;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
class TicketRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TicketRepository ticketRepository;

    @BeforeEach
    void clear(){
        ticketRepository.deleteAll();
    }

    @Test
    public void findByIdShouldReturnGoodObject() {
        // given
        Ticket ticket = new Ticket(1, BigDecimal.TEN);
        entityManager.persist(ticket);
        entityManager.flush();

        // when
        int actual = ticketRepository.findById(ticket.getId()).get().getSeatNumber();
        int expected = ticket.getSeatNumber();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void saveTest() {
        // given
        Ticket ticket = new Ticket(1, BigDecimal.TEN);
        Ticket ticket1 = ticketRepository.save(ticket);

        // when
        int actual = ticketRepository.findById(ticket1.getId()).get().getSeatNumber();
        int expected = ticket.getSeatNumber();

        // then
        assertEquals(expected, actual);
    }

}