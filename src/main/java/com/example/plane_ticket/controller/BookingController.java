package com.example.plane_ticket.controller;

import com.example.plane_ticket.module.Booking;
import com.example.plane_ticket.service.BookingRequest;
import jakarta.validation.Valid;
import org.apache.logging.log4j.message.Message;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookingController {
    @Autowired
    private BookingRequest request;

    @GetMapping("/booking")
    public ResponseEntity<List<Booking>> getAllBooking(){
        return ResponseEntity.ok(request.getAll());
    }

    @PostMapping("/api/bookings")
    public ResponseEntity<String> addBooking(@Valid @RequestBody Booking booking){
        request.addBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dat ve thanh cong");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex){

            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });

            return errors;
    }
}
