package pl.artimerek.ticketbookingapp.service;

import pl.artimerek.ticketbookingapp.model.Event;
import pl.artimerek.ticketbookingapp.model.Ticket;

import java.util.List;
import java.util.Set;

public interface TicketService {

    List<Ticket> findAll();

    Ticket getById(Long ticketId);

    void save(Ticket ticket);

    Ticket findById(Long ticketId);

    void deleteById(Long ticketId);

    void setUser(String name);

    Set<Ticket> generateTickets(Event event, int amount);

    String getUser();

    List<Ticket> findAllFreeTickets();
}
