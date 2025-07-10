package com.example.plane_ticket.repo;
import com.example.plane_ticket.module.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Integer> {
    @Query ("SELECT b FROM Booking b WHERE b.agreeTerms = ?1 ORDER BY b.flightDate asc ")
    List<Booking> findByAgreeTermsOderByFlightDateASC(Boolean agreeTerms);
    @Query ("SELECT b FROM Booking b WHERE b.agreeTerms = ?1 ORDER BY b.flightDate DESC ")
    List<Booking> findByAgreeTermsOderByFlightDateDESC(Boolean agreeTerms);

}
