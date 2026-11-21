package com.example.bibliotheque_api.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
public ResponseEntity<Map<String, String>> handleResponseStatus(ResponseStatusException ex) {

    return ResponseEntity.status(ex.getStatusCode()).body(Map.of("msg", ex.getReason()));

}

   @ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, List<String>>> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex) {

    List<String> messages = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getDefaultMessage())
            .toList();

    return ResponseEntity.status(ex.getStatusCode()).body(Map.of("msg", messages));
}

}