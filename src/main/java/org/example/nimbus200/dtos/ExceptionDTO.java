package org.example.nimbus200.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDTO {
    private String errorMessage;
    private String resolutionMessage;
    private ResponseStatus responseStatus;

}
