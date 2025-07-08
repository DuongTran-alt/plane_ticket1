package com.example.plane_ticket.service;

import com.example.plane_ticket.module.Booking;
import com.example.plane_ticket.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingRequest {
    @Autowired
    private BookingRepo repo;

    public List<Booking> getAll(){
        return repo.findAll();
    }
    public void addBooking(Booking booking){
        repo.save(booking);
    }

    public Booking findById(int id){
        return repo.findById(id).orElse(null);
    }

    public void upDate(Booking booking){
        repo.save(booking);
    }

    public void deleteBooking(int id){
        repo.deleteById(id);
    }
}

