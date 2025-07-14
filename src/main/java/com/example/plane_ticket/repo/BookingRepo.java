package com.example.plane_ticket.repo;
import com.example.plane_ticket.module.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Integer> {
    @Query ("SELECT b FROM Booking b WHERE b.agreeTerms = ?1 ORDER BY b.flightDate ASC ")
    List<Booking> findByAgreeTermsOderByFlightDateASC(Boolean agreeTerms);
    @Query ("SELECT b FROM Booking b WHERE b.agreeTerms = ?1 ORDER BY b.flightDate DESC ")
    List<Booking> findByAgreeTermsOderByFlightDateDESC(Boolean agreeTerms);

    //Can phai co cau lenh coutQuery de dem tong so trang, native query la cho phep dien theo cau lenh chuan tren sql
    //b.ticketQuantity = :quantity se giong voi gia tri trong value
    //(b.ticketQuantity = :quantity or :quantity is null) De cho cau lenh van dung khi ma khong nhap gia tri cho bien
    // -> Cau lenh van thuc hien duoc
    // De Interger de co the dien gia tri null vao
    // int khong nhan null ma cho default = 0
    @Query(value = "select b from Booking b where  (b.ticketQuantity = :quantity or :quantity is null) and (b.fullName like %:name% or :name is null) and (b.flightDate = :flightDate or :flightDate is null) and (b.phone = :phone or :phone is null )",countQuery = "select count(b) from Booking b where  (b.ticketQuantity = :quantity or :quantity is null) and (b.fullName like %:name% or :name is null) and (b.flightDate = :flightDate or :flightDate is null) and (b.phone = :phone or :phone is null )")
    Page<Booking> search(@Param(value = "quantity") Integer ticketQuantity, @Param(value = "name") String name, @Param(value = "flightDate") LocalDate flightDate, @Param(value = "phone") String phone, Pageable pageable);
}
