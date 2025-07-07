package com.example.plane_ticket.service;

import com.example.plane_ticket.module.Ticket;
import com.example.plane_ticket.repo.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepo repo;

    public List<Ticket> getAll(){
        return repo.findAll();
    }

    public Ticket findById (int id) {
        return repo.findById(id).orElse(null);
    }

    public void add (Ticket ticket){
        repo.save(ticket);
    }

    public void adds (List<Ticket> tickets){
        repo.saveAll(tickets);
    }

    public void update (Ticket ticket){
        repo.save(ticket);
    }

    public void delete (int id){
        repo.deleteById(id);
    }
}
