package com.example.plane_ticket.controller;

import com.example.plane_ticket.module.Booking;
import com.example.plane_ticket.module.DTO.BookingDTO;
import com.example.plane_ticket.module.DTO.BookingFilterRequest;
import com.example.plane_ticket.service.BookingRequest;
import jakarta.validation.Valid;
import org.apache.logging.log4j.message.Message;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookingController {
    @Autowired
    private BookingRequest request;

    @GetMapping("/api/search-bookings")
    //@RequestParam de cho nguoi dung nhap tham so dau vao, required false khien khi nguoi dung khong nhap gia tri gi thi no cho la null
    public ResponseEntity<Page<BookingDTO>> getAllBooking(@RequestParam(name = "ticketQuantity",required = false) Integer quantity, @RequestParam(name = "fullName",required = false) String fullName, @RequestParam(name = "flightDate",required = false) LocalDate flightDate, @RequestParam(name = "phone", required = false) String phone, Pageable pageable){
        return ResponseEntity.ok(request.searchBooking(quantity,fullName,flightDate,phone,pageable));
    }

    @PostMapping("/api/getall-bookings")
    public ResponseEntity<List<BookingDTO>> getAllBooking(@Valid @RequestBody BookingFilterRequest bookingFilterRequest){
        if (request.filterByFlightDate(bookingFilterRequest) == null){
            return ResponseEntity.badRequest().build();
        }
        else
            return ResponseEntity.ok(request.filterByFlightDate(bookingFilterRequest));
    }

    @PostMapping("/api/bookings")
    public ResponseEntity<String> addBooking(@Valid @RequestBody BookingDTO dto){
        if (request.addBooking(dto))
        return ResponseEntity.status(HttpStatus.CREATED).body("Dat ve thanh cong");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ve da bi dat");
    }

    //Khi một người dùng gửi request (POST/PUT...) kèm dữ liệu sai định dạng hoặc thiếu,
    //Spring sẽ ném ra lỗi MethodArgumentNotValidException.
    //Method dưới đây sẽ bắt lỗi đó và trả về map chứa tên field và lỗi tương ứng
    //Báo cho Spring biết: nếu exception này xảy ra, hãy trả về mã HTTP 400 (Bad Request).
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    //Đánh dấu phương thức này là một handler cho exception loại
    //MethodArgumentNotValidException, tức là khi validation fail (như @NotNull, @Email, @Size,...) thì sẽ vào đây.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    //Tên method có thể đặt tùy ý. Tham số ex chính là object chứa thông tin lỗi.
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex){
    //Tạo một Map để lưu tên field và thông báo lỗi tương ứng.
        //HashMap là một cấu trúc dữ liệu trong Java lưu trữ các cặp (key-value).
            Map<String, String> errors = new HashMap<>();
            //Duyệt qua tất cả lỗi trong binding result (kết quả validate thất bại):
        //error.getField() → tên field gây lỗi (vd: "email")
        //error.getDefaultMessage() → thông báo lỗi từ annotation (vd: "Không được để trống")
            ex.getBindingResult().getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });

            return errors;
    }
}
