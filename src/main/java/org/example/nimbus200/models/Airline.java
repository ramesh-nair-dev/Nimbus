package org.example.nimbus200.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Airline extends BaseClass {
    private String airLineName;
    private List<Flight> airLineFlights;
}
