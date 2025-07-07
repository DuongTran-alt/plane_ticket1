package com.example.plane_ticket.repo;

import com.example.plane_ticket.module.Seat;
import com.example.plane_ticket.module.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepo extends JpaRepository<Seat,Integer> {
}
