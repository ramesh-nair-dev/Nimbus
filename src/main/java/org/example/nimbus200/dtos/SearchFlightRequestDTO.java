package org.example.nimbus200.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchFlightRequestDTO {
    private String origin;
    private String destination;
}
