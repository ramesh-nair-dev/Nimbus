package org.example.nimbus200.controllers;

import org.example.nimbus200.dtos.BookingFlightRequestDTO;
import org.example.nimbus200.dtos.BookingFlightResponseDTO;
import org.example.nimbus200.exceptions.BookingNotFoundException;
import org.example.nimbus200.exceptions.SeatAlreadyBookedException;
import org.example.nimbus200.exceptions.SeatNotFoundException;
import org.example.nimbus200.services.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingFlightController {

    private final BookingService bookingService;

    public BookingFlightController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingFlightResponseDTO> bookFlight(
            @RequestBody BookingFlightRequestDTO request
    ) throws SeatNotFoundException, SeatAlreadyBookedException {
        BookingFlightResponseDTO response = bookingService.bookFlight(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<BookingFlightResponseDTO> cancelBooking(
            @PathVariable Long bookingId
    ) throws BookingNotFoundException {
        BookingFlightResponseDTO response = bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok(response);
    }
}
