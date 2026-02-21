package org.example.nimbus200.repository;

import org.example.nimbus200.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<Flight> findByDepartureAirportAndArrivalAirport(String departureAirport, String arrivalAirport);
}
