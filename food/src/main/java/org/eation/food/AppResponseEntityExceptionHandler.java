package org.eation.food;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@ControllerAdvice
@RestController
public class AppResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .message("Validation Failed")
                .errors(ex.getBindingResult().getFieldErrors().stream()
                        .map(field -> ErrorDetails.FieldError.builder()
                                .fieldName(field.getField())
                                .message(field.getDefaultMessage())
                                .build())
                        .sorted(comparing(ErrorDetails.FieldError::getFieldName))
                        .collect(toList()))
                .build();
        return ResponseEntity.badRequest().body(errorDetails);
    }
}
