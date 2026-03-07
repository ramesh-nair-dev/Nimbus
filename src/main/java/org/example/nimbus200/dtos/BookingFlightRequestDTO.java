package org.example.nimbus200.dtos;

public record BookingFlightRequestDTO(
        Long flightId,
        Long userId,
        Long seatId
) {
}
