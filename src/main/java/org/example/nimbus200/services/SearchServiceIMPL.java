package org.example.nimbus200.services;

import org.example.nimbus200.dtos.SearchFlightRequestDTO;
import org.example.nimbus200.dtos.SearchFlightResponseDTO;
import org.example.nimbus200.exceptions.NoFlightsFoundExceptions;
import org.example.nimbus200.models.Flight;
import org.example.nimbus200.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SearchServiceIMPL implements SearchService {
    private FlightRepository flightRepository;

    public SearchServiceIMPL(
            FlightRepository flightRepository
    ) {
        this.flightRepository = flightRepository;

    }

    @Override
    public SearchFlightResponseDTO searchFlights(SearchFlightRequestDTO searchFlightRequestDTO) throws NoFlightsFoundExceptions {
        Optional<Flight> searchedFlight = flightRepository.findByDepartureAirportAndArrivalAirport(
                searchFlightRequestDTO.getOrigin(),
                searchFlightRequestDTO.getDestination()
        );
        if(searchedFlight.isEmpty()){
            throw new NoFlightsFoundExceptions("No flights found for the given origin and destination");
        }

        Flight flight = searchedFlight.get();
        return SearchFlightResponseDTO.from(flight);

    }
}
