package org.example.nimbus200.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.nimbus200.models.Flight;

@Getter
@Setter
public class SearchFlightResponseDTO {
    private String flightNumber;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;

    public static SearchFlightResponseDTO from(Flight flight){
        SearchFlightResponseDTO responseDTO = new SearchFlightResponseDTO();
        responseDTO.setFlightNumber(flight.getFlightNumber());
        responseDTO.setOrigin(flight.getDepartureAirport());
        responseDTO.setDestination(flight.getArrivalAirport());
        responseDTO.setDepartureTime(flight.getDepartureTime());
        responseDTO.setArrivalTime(flight.getArrivalTime());
        return responseDTO;
    }
}
