package pl.artimerek.ticketbookingapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class EventHelper {
    private String name;
    private String placeName;
    private String date;
    private int ticketsAmount;
}
