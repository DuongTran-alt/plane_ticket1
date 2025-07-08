package com.example.plane_ticket.module.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingFilterRequest {
    @Valid

    @AssertTrue(message = "Bạn phải đồng ý với điều khoản")
    private Boolean agreeTerms;

    @NotBlank (message = "Sap xep theo chieu nguoc hoac xuoi (ASC/DESC)")
    private String sortFilter = "ASC";
}
