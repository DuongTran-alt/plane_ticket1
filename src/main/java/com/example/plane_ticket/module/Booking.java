    package com.example.plane_ticket.module;

    import com.fasterxml.jackson.annotation.JsonFormat;
    import jakarta.persistence.*;
    import jakarta.validation.Valid;
    import jakarta.validation.constraints.*;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.time.LocalDate;
    import java.util.List;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Entity
    public class Booking {

        @Valid

        @NotNull(message = "Khong duoc de trong ho va ten")
        @NotBlank(message = "Khong duoc de trong ho va ten")
        @Size(min = 3, max = 50, message = "độ dài 3–50 ký tự")
        private String fullName;

        @Column(name = "booking_email")
        @Id
        @NotNull(message = "Khong duoc de email")
        @NotBlank(message = "Khong duoc de email")
        @Email(message = "Phai dien đúng định dạng email")
        private String email;

        @NotNull(message = "Ban chua dien so dien thoai")
        @Pattern(regexp = "\\d{10}", message = "Số điện thoại phải đúng 10 chữ số")
        private String phone;

        @NotNull(message = "Khong duoc de trong so ve dat")
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

        //Luu booking, cac ticket lquan cx se duoc luu
        @OneToMany (mappedBy = "booking", cascade = CascadeType.ALL)
        private List<Ticket> ticket;
    }
