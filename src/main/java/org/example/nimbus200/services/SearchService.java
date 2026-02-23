package org.example.nimbus200.services;

import org.example.nimbus200.dtos.SearchFlightRequestDTO;
import org.example.nimbus200.dtos.SearchFlightResponseDTO;
import org.example.nimbus200.exceptions.NoFlightsFoundExceptions;

public interface SearchService {
    SearchFlightResponseDTO searchFlights(SearchFlightRequestDTO searchFlightRequestDTO) throws NoFlightsFoundExceptions;
}
