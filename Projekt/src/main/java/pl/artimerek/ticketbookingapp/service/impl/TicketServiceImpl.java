package pl.artimerek.ticketbookingapp.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import pl.artimerek.ticketbookingapp.model.Ticket;
import pl.artimerek.ticketbookingapp.repository.TicketRepository;
import pl.artimerek.ticketbookingapp.service.TicketService;

import java.util.List;
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

    public List<Ticket> findAllFreeTickets(){
        return ticketRepository.findAll().stream()
                .filter(ticket -> ticket.getUser() == null)
                .collect(Collectors.toList());
    }

}
