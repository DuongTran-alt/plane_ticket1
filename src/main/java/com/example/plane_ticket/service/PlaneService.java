package com.example.plane_ticket.service;

import com.example.plane_ticket.module.Plane;
import com.example.plane_ticket.module.Seat;
import com.example.plane_ticket.repo.PlaneRepo;
import com.example.plane_ticket.repo.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaneService {
    @Autowired
    private PlaneRepo repo;

    public List<Plane> getAll(){
        return repo.findAll();
    }

    public Plane findById (int id) {
        return repo.findById(id).orElse(null);
    }

    public void add (Plane plane){
        repo.save(plane);
    }

    public void adds (List<Plane> planes){
        repo.saveAll(planes);
    }

    public void update (Plane plane){
        repo.save(plane);
    }

    public void delete (int id){
        repo.deleteById(id);
    }
}
