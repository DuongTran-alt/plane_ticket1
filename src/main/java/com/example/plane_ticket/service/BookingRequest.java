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
//            if (isSeatTaken(booking)) {
//                return false;
//            }
            repo.save(booking);
            return true;
    }

//    private boolean isSeatTaken(Booking booking) {
//            // Kiểm tra trong DB
//            return true;
//    }

    public List<Booking> filterByFightDate(BookingFilterRequest request) {
        //Lay yeu cau sap xep
        String sortDir = request.getSortFilter();
        //Sort theo cot flightDate trong DB
        Sort sort = Sort.by("flightDate");
        //Từ lớn đến nhỏ nếu trùng với DESC không thì ngược lại
        sort = "DESC".equalsIgnoreCase(sortDir) ? sort.descending() : sort.ascending();

        //Xét nêú agreeTerm == null
        if (request.getAgreeTerms() == null) {
            return null;
        }

        // Nếu lọc theo agreeTerms
        return repo.findByAgreeTerms(request.getAgreeTerms(), sort);
    }
}

