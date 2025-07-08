package com.example.plane_ticket.repo;

import com.example.plane_ticket.module.Booking;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Integer> {
    List<Booking> findByAgreeTerms(Boolean agreeTerms, Sort sort);
}
