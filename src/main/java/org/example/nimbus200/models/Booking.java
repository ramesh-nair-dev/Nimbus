package org.example.nimbus200.models;

import jakarta.persistence.Entity;

@Entity
public class Booking extends BaseClass {
    private User user;
    private Flight flight;
    private Seats seat;
    private BookingStatus bookingStatus;
}
