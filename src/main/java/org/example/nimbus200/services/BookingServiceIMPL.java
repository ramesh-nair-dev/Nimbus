package org.example.nimbus200.services;

import org.example.nimbus200.dtos.BookingFlightRequestDTO;
import org.example.nimbus200.dtos.BookingFlightResponseDTO;
import org.example.nimbus200.dtos.ResponseStatus;
import org.example.nimbus200.exceptions.BookingNotFoundException;
import org.example.nimbus200.exceptions.SeatAlreadyBookedException;
import org.example.nimbus200.exceptions.SeatNotFoundException;
import org.example.nimbus200.models.*;
import org.example.nimbus200.repository.BookingRepository;
import org.example.nimbus200.repository.FlightRepository;
import org.example.nimbus200.repository.SeatRepository;
import org.example.nimbus200.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingServiceIMPL implements BookingService {

    private final SeatRepository seatRepository;
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    public BookingServiceIMPL(
            SeatRepository seatRepository,
            BookingRepository bookingRepository,
            FlightRepository flightRepository,
            UserRepository userRepository
    ) {
        this.seatRepository = seatRepository;
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
    }

    /**
     * Books a flight seat using pessimistic locking to prevent double-booking.
     *
     * Concurrency flow:
     * 1. Acquire an exclusive row-level lock on the seat (SELECT FOR UPDATE)
     * 2. Any concurrent transaction trying to lock the SAME seat will BLOCK here
     * 3. Once the lock is acquired, check if the seat is still AVAILABLE
     * 4. If yes → mark as BOOKED, create booking, commit (releases lock)
     * 5. If no → throw SeatAlreadyBookedException (the other thread got it first)
     */
    @Override
    @Transactional
    public BookingFlightResponseDTO bookFlight(BookingFlightRequestDTO request)
            throws SeatNotFoundException, SeatAlreadyBookedException {

        // Step 1: Acquire PESSIMISTIC_WRITE lock on the seat row
        // This translates to: SELECT * FROM seats WHERE id = ? FOR UPDATE
        // If another transaction already holds this lock, we BLOCK here until it commits/rollbacks
        Seats seat = seatRepository.findByIdWithLock(request.seatId())
                .orElseThrow(() -> new SeatNotFoundException(
                        "Seat not found with id: " + request.seatId()));

        // Step 2: After acquiring lock, validate the seat is still available
        // This is safe because no other transaction can modify this row while we hold the lock
        if (seat.getSeatStatus() != SeatStatus.AVAILABLE) {
            throw new SeatAlreadyBookedException(
                    "Seat " + seat.getSeatNumber() + " is already " + seat.getSeatStatus());
        }

        // Step 3: Mark seat as BOOKED (we hold the lock, so this is safe)
        seat.setSeatStatus(SeatStatus.BOOKED);
        seatRepository.save(seat);

        // Step 4: Load related entities and create the booking
        Flight flight = flightRepository.findById(request.flightId())
                .orElseThrow(() -> new SeatNotFoundException(
                        "Flight not found with id: " + request.flightId()));

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new SeatNotFoundException(
                        "User not found with id: " + request.userId()));

        Booking booking = new Booking();
        booking.setFlight(flight);
        booking.setUser(user);
        booking.setSeat(seat);
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        booking = bookingRepository.save(booking);

        // Step 5: Transaction commits here → lock is released
        // The blocked thread (if any) now unblocks and sees seatStatus = BOOKED
        return BookingFlightResponseDTO.from(booking, ResponseStatus.SUCCESS,
                "Booking confirmed for seat " + seat.getSeatNumber());
    }

    /**
     * Cancels a booking and releases the seat back to AVAILABLE.
     * Also uses pessimistic locking on the seat to prevent race conditions during cancellation.
     */
    @Override
    @Transactional
    public BookingFlightResponseDTO cancelBooking(Long bookingId) throws BookingNotFoundException {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException(
                        "Booking not found with id: " + bookingId));

        // Lock the seat before modifying to prevent concurrent cancel + book race
        Seats seat = seatRepository.findByIdWithLock(booking.getSeat().getId())
                .orElseThrow(() -> new BookingNotFoundException("Associated seat not found"));

        booking.setBookingStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        seat.setSeatStatus(SeatStatus.AVAILABLE);
        seatRepository.save(seat);

        return BookingFlightResponseDTO.from(booking, ResponseStatus.SUCCESS,
                "Booking cancelled, seat " + seat.getSeatNumber() + " is now available");
    }
}
