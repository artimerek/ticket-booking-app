package pl.artimerek.ticketbookingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.artimerek.ticketbookingapp.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
