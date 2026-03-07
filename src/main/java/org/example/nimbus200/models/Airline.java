package org.example.nimbus200.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Airline extends BaseClass {
    private String airLineName;

    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL)
    private List<Flight> airLineFlights = new ArrayList<>();
}
