package org.example.nimbus200.models;

import jakarta.persistence.Entity;

@Entity
public class Seats extends BaseClass {
    private String seatNumber;
    private SeatClass seatClass;
}
