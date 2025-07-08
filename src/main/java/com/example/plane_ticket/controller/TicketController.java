package com.example.plane_ticket.controller;

import com.example.plane_ticket.module.Ticket;
import com.example.plane_ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.IllegalFormatCodePointException;
import java.util.List;

@RestController
public class TicketController {

    @Autowired
    private TicketService service;
    @GetMapping("/ticket")
    public ResponseEntity<List<Ticket>> getAllTicket(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable int id){
        Ticket ticket = service.findById(id) ;
        if (ticket != null)
        return ResponseEntity.ok(ticket);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/addticket")
    public ResponseEntity<String> addTicket(@RequestBody Ticket ticket){
        service.add(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body("Da them ve");
    }
}
