package pl.artimerek.ticketbookingapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.artimerek.ticketbookingapp.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
