package org.example.nimbus200.dtos;

import org.example.nimbus200.models.Booking;

public record BookingFlightResponseDTO(
        ResponseStatus status,
        String message,
        Long bookingId,
        String seatNumber,
        String flightNumber
) {
    public static BookingFlightResponseDTO from(Booking booking, ResponseStatus status, String message) {
        return new BookingFlightResponseDTO(
                status,
                message,
                booking.getId(),
                booking.getSeat().getSeatNumber(),
                booking.getFlight().getFlightNumber()
        );
    }
}
