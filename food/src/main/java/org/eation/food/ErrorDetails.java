package org.eation.food;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Value
@Builder
public class ErrorDetails {

    private Date timestamp;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FieldError> errors;


    @Value
    @Builder
    public static class FieldError {
        private String fieldName;
        private String message;
    }
}
