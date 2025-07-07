package com.example.plane_ticket.repo;

import com.example.plane_ticket.module.Ticket;
import com.example.plane_ticket.module.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
}
