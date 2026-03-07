package org.example.nimbus200.controllerAdvice;

import org.example.nimbus200.dtos.ExceptionDTO;
import org.example.nimbus200.dtos.ResponseStatus;
import org.example.nimbus200.exceptions.BookingNotFoundException;
import org.example.nimbus200.exceptions.NoFlightsFoundExceptions;
import org.example.nimbus200.exceptions.SeatAlreadyBookedException;
import org.example.nimbus200.exceptions.SeatNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SearchFlightExceptionHandler {

    @ExceptionHandler(NoFlightsFoundExceptions.class)
    public ResponseEntity<ExceptionDTO> handleNoFlightsFoundExceptions(NoFlightsFoundExceptions e){
        ExceptionDTO dto = new ExceptionDTO();
        dto.setErrorMessage(e.getMessage());
        dto.setResolutionMessage("Please try searching with different origin and destination or try again later.");
        dto.setResponseStatus(ResponseStatus.FAILURE);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
    }

    @ExceptionHandler(SeatAlreadyBookedException.class)
    public ResponseEntity<ExceptionDTO> handleSeatAlreadyBooked(SeatAlreadyBookedException e) {
        ExceptionDTO dto = new ExceptionDTO();
        dto.setErrorMessage(e.getMessage());
        dto.setResolutionMessage("This seat is no longer available. Please choose a different seat.");
        dto.setResponseStatus(ResponseStatus.FAILURE);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(dto);
    }

    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleSeatNotFound(SeatNotFoundException e) {
        ExceptionDTO dto = new ExceptionDTO();
        dto.setErrorMessage(e.getMessage());
        dto.setResolutionMessage("The requested resource was not found.");
        dto.setResponseStatus(ResponseStatus.FAILURE);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleBookingNotFound(BookingNotFoundException e) {
        ExceptionDTO dto = new ExceptionDTO();
        dto.setErrorMessage(e.getMessage());
        dto.setResolutionMessage("The booking could not be found. Please verify your booking ID.");
        dto.setResponseStatus(ResponseStatus.FAILURE);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
    }
}
