package com.example.plane_ticket.service;

import com.example.plane_ticket.module.Seat;
import com.example.plane_ticket.module.Ticket;
import com.example.plane_ticket.repo.SeatRepo;
import com.example.plane_ticket.repo.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    private SeatRepo repo;

    public List<Seat> getAll(){
        return repo.findAll();
    }

    public Seat findById (int id) {
        return repo.findById(id).orElse(null);
    }

    public void add (Seat seat){
        repo.save(seat);
    }

    public void adds (List<Seat> seats){
        repo.saveAll(seats);
    }

    public void update (Seat seat){
        repo.save(seat);
    }

    public void delete (int id){
        repo.deleteById(id);
    }
}
