package com.example.plane_ticket.service;

import com.example.plane_ticket.model.Booking;
import com.example.plane_ticket.model.DTO.BookingDTO;
import com.example.plane_ticket.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.plane_ticket.model.DTO.BookingFilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingRequest {
    @Autowired
    private BookingRepo repo;

    public List<Booking> getAll(){
        return repo.findAll();
    }

    public Booking findById(int id){
        return repo.findById(id).orElse(null);
    }

    public void upDate(Booking booking){
        repo.save(booking);
    }

    public void deleteBooking(int id) {
        repo.deleteById(id);
    }

    public boolean addBooking(BookingDTO dto) {
            // Giả sử kiểm tra xem ghế đã có người đặt chưa
//            if (isSeatTaken(booking)) {
//                return false;
//            }
        Booking booking = new Booking();
          booking.setFullName(dto.getFullName());
          booking.setEmail(dto.getEmail());
          booking.setBirthDate(dto.getBirthDate());
          booking.setPhone(dto.getPhone());
          booking.setAgreeTerms(dto.isAgreeTerms());
          booking.setTicketQuantity(dto.getTicketQuantity());
          booking.setFlightDate(dto.getFlightDate());
            repo.save(booking);
            return true;
    }

//    private boolean isSeatTaken(Booking booking) {
//            // Kiểm tra trong DB
//            return true;
//    }

    public BookingDTO toDTO (Booking booking){
        BookingDTO dto = new BookingDTO();
        dto.setFullName(booking.getFullName());
        dto.setEmail(booking.getEmail());
        dto.setPhone(booking.getPhone());
        dto.setTicketQuantity(booking.getTicketQuantity());
        dto.setBirthDate(booking.getBirthDate());
        dto.setFlightDate(booking.getFlightDate());
        dto.setAgreeTerms(booking.isAgreeTerms());
        return dto;
    }
    public List<BookingDTO> convertToDTO() {
        List<Booking> bookings = repo.findAll();
        return bookings.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<BookingDTO> filterByFlightDate(BookingFilterRequest request){
        if (request.getSortFilter().equalsIgnoreCase("DESC")){
            return repo.findByAgreeTermsOderByFlightDateDESC(request.getAgreeTerms()).stream().map(this::toDTO).collect(Collectors.toList());
        }
        else
            return repo.findByAgreeTermsOderByFlightDateASC(request.getAgreeTerms()).stream().map(this::toDTO).collect(Collectors.toList());
    }
    public Page<BookingDTO> searchBooking(Integer quantity, String name, LocalDate flightDate, String phone, Pageable pageable){
//        String sortBy = "";
//        String direction = "DESC";
//        //Sắp xếp theo thứ tự từ lớn đêến bé hoặc lớn đến bé
//        Sort sort = direction.equalsIgnoreCase("DESC")
//                //Sắp xếp theo cột (sortBy)
//                ? Sort.by(sortBy).descending()
//                : Sort.by(sortBy).ascending();
        Page<Booking> bookingPage = repo.search(quantity, name, flightDate, phone, pageable);
        return bookingPage.map(this::toDTO); // map Page -> Page
    }
}

