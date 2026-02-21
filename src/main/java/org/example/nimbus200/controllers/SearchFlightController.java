package org.example.nimbus200.controllers;

import org.example.nimbus200.dtos.SearchFlightRequestDTO;
import org.example.nimbus200.dtos.SearchFlightResponseDTO;
import org.example.nimbus200.exceptions.NoFlightsFoundExceptions;
import org.example.nimbus200.services.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchFlightController {
    private SearchService searchService;
    public SearchFlightController(
            SearchService searchService
    ) {
        this.searchService = searchService;
    }
    @PostMapping
    public ResponseEntity<SearchFlightResponseDTO> searchFlights(
            @RequestBody SearchFlightRequestDTO searchFlightRequestDTO
    ) throws NoFlightsFoundExceptions {
        SearchFlightResponseDTO searchFlightResponseDTO = searchService.searchFlights(searchFlightRequestDTO);
        return ResponseEntity.ok(searchFlightResponseDTO);
    }
}
