package org.example.nimbus200.controllerAdvice;

import org.example.nimbus200.dtos.ExceptionDTO;
import org.example.nimbus200.dtos.ResponseStatus;
import org.example.nimbus200.exceptions.NoFlightsFoundExceptions;
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
        return ResponseEntity.status(404).body(dto);
    }

}
