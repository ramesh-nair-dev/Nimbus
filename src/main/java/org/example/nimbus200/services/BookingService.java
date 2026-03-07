package org.example.nimbus200.services;

import org.example.nimbus200.dtos.BookingFlightRequestDTO;
import org.example.nimbus200.dtos.BookingFlightResponseDTO;
import org.example.nimbus200.exceptions.BookingNotFoundException;
import org.example.nimbus200.exceptions.SeatAlreadyBookedException;
import org.example.nimbus200.exceptions.SeatNotFoundException;

public interface BookingService {
    BookingFlightResponseDTO bookFlight(BookingFlightRequestDTO request)
            throws SeatNotFoundException, SeatAlreadyBookedException;

    BookingFlightResponseDTO cancelBooking(Long bookingId)
            throws BookingNotFoundException;
}
