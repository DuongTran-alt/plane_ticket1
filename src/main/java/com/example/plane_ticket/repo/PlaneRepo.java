package com.example.plane_ticket.repo;

import com.example.plane_ticket.module.Plane;
import com.example.plane_ticket.module.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneRepo extends JpaRepository<Plane,Integer> {
}
