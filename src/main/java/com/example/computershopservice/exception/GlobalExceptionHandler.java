package com.example.computershopservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public Object entityNotFoundHandler(EntityNotFoundException ex) {
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("message", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());
        body.put("code", HttpURLConnection.HTTP_NOT_FOUND);

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingDataException.class)
    public Object missingDataHandler(MissingDataException ex) {
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("message", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());
        body.put("code", HttpURLConnection.HTTP_BAD_REQUEST);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public Object entityAlreadyExistsHandler(EntityAlreadyExistsException ex) {
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("message", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());
        body.put("code", HttpURLConnection.HTTP_BAD_REQUEST);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
