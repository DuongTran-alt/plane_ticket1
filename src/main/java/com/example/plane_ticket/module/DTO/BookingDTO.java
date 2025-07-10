package com.example.plane_ticket.module.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDTO {
    //@NotNull	Mọi object (String, List,...)	Không được phép là null, nhưng có thể là rỗng ("", [],...)
    //@NotEmpty	Collection, Array, String	Không được là null và không được rỗng
    //@NotBlank	Chỉ áp dụng cho String	Không được là null, không rỗng, và không chỉ chứa khoảng trắng
    @Valid
    @NotBlank(message = "Khong duoc de trong ho va ten")
    @Size(min = 3, max = 50, message = "độ dài 3–50 ký tự")
    private String fullName;
    @NotBlank(message = "Khong de trong email")
    @Email
    private String email;
    @NotBlank(message = "Chua dien so dien thoai")
    @Pattern(regexp = "\\d{10}", message = "Vui long dien du 10 chu so")
    private String phone;

    //Dung cho nhieu loai du lieu
    //"" van hop le vi chuoi khong null
    @NotNull(message = "Khong duoc de trong so ve dat")
    //Danh cho int, double, ...
    @Min(value = 1, message = "Phai dat tren 1 ve")
    @Max(value = 10, message = "Phai dat duoi 10 ve")
    private int ticketQuantity;

    @NotNull(message = "Ngày sinh không được để trống")
    @Past(message = "Ngày sinh phải là trong quá khứ")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    @NotNull(message = "Ngày bay không được để trống")
    @Future(message = "Ngày bay phải là trong tương lai")
    private LocalDate flightDate;

    @AssertTrue(message = "Bạn phải đồng ý với điều khoản")
    private boolean agreeTerms;
}
