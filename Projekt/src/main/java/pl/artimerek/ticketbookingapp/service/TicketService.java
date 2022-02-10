package pl.artimerek.ticketbookingapp.service;

import pl.artimerek.ticketbookingapp.model.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> findAll();

    Ticket getById(Long ticketId);

    void save(Ticket ticket);

    Ticket findById(Long ticketId);

    void deleteById(Long ticketId);

    void setUser(String name);

    String getUser();

    List<Ticket> findAllFreeTickets();
}
