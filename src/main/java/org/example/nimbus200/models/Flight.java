package org.example.nimbus200.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Flight extends BaseClass {
    private String flightNumber;
    private Airline airline;
    private String departureAirport;
    private String arrivalAirport;
    private String departureTime;
    private String arrivalTime;
    List<Seats> flightSeats;
}
