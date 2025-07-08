package com.example.plane_ticket.controller;

import com.example.plane_ticket.module.Booking;
import com.example.plane_ticket.service.BookingRequest;
import jakarta.validation.Valid;
import org.apache.logging.log4j.message.Message;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookingController {
    @Autowired
    private BookingRequest request;

    @GetMapping("/booking")
    public ResponseEntity<List<Booking>> getAllBooking(){
        return ResponseEntity.ok(request.getAll());
    }

    @PostMapping("/api/bookings")
    public ResponseEntity<String> addBooking(@Valid @RequestBody Booking booking){
        request.addBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dat ve thanh cong");
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
