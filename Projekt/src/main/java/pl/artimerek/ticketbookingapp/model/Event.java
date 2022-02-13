package pl.artimerek.ticketbookingapp.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String placeName;
    private LocalDate date;

    @OneToMany(mappedBy = "event"  , cascade=CascadeType.ALL)
    private Set<Ticket> tickets = new HashSet<>();

    public Event(String name, String placeName, LocalDate date) {
        this.name = name;
        this.placeName = placeName;
        this.date = date;
    }

}
