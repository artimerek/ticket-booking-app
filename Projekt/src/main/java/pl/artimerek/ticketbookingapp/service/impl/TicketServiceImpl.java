package pl.artimerek.ticketbookingapp.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import pl.artimerek.ticketbookingapp.model.Event;
import pl.artimerek.ticketbookingapp.model.Ticket;
import pl.artimerek.ticketbookingapp.repository.TicketRepository;
import pl.artimerek.ticketbookingapp.service.TicketService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private String user;

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getById(Long ticketId) {
        return ticketRepository.getById(ticketId);
    }

    @Override
    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public Ticket findById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void deleteById(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }

    @Override
    public Set<Ticket> generateTickets(Event event, int amount) {

        Set<Ticket> ticketSet = new HashSet<>();

        for (int i = 1; i <= amount; i++) {
            BigDecimal random = generateRandomBigDecimalFromRange(
                    BigDecimal.TEN,
                    BigDecimal.valueOf(100));
            Ticket ticket = new Ticket(i, random);
            ticket.setEvent(event);
            save(ticket);
            ticketSet.add(ticket);
        }

        return ticketSet;
    }

    public List<Ticket> findAllFreeTickets(){
        return ticketRepository.findAll().stream()
                .filter(ticket -> ticket.getUser() == null)
                .filter(ticket -> ticket.getEvent().getDate().isAfter(LocalDate.now()))
                .collect(Collectors.toList());
    }

    private BigDecimal generateRandomBigDecimalFromRange(BigDecimal min, BigDecimal max) {
        BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random())
                .multiply(max.subtract(min)));
        return randomBigDecimal.setScale(2, RoundingMode.HALF_UP);
    }

}
