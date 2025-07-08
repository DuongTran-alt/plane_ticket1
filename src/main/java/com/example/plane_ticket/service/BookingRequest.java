package com.example.plane_ticket.service;

import com.example.plane_ticket.module.Booking;
import com.example.plane_ticket.module.DTO.BookingFilterRequest;
import com.example.plane_ticket.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingRequest {
    @Autowired
    private BookingRepo repo;

    public List<Booking> getAll(){
        return repo.findAll();
    }

    public boolean addBooking(Booking booking) {
            // Giả sử kiểm tra xem ghế đã có người đặt chưa
            if (isSeatTaken(booking)) {
                return false;
            }
            repo.save(booking);
            return true;
    }

    private boolean isSeatTaken(Booking booking) {
            // Kiểm tra trong DB
            return true;
    }

    public List<Booking> filterByFightDate(BookingFilterRequest request) {
        String sortDir = request.getSortFilter();
        Sort sort = Sort.by("flightDate");
        sort = "DESC".equalsIgnoreCase(sortDir) ? sort.descending() : sort.ascending();

        if (request.getAgreeTerms() == null) {
            return repo.findAll(sort);
        }

        // Nếu lọc theo agreeTerms
        return repo.findByAgreeTerms(request.getAgreeTerms(), sort);
    }
}

