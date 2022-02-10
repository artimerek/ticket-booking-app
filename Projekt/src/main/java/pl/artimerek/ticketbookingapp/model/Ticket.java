package pl.artimerek.ticketbookingapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int seatNumber;
    private BigDecimal price;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private Event event;

    public Ticket(int seatNumber, BigDecimal price, User user, Event event) {
        this.seatNumber = seatNumber;
        this.price = price;
        this.user = user;
        this.event = event;
    }

    public Ticket(int seatNumber, BigDecimal price) {
        this.seatNumber = seatNumber;
        this.price = price;
    }
}
