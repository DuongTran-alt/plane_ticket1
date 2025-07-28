package com.example.plane_ticket.model.DTO;

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

    private Boolean agreeTerms;

    private String sortFilter = "ASC";
}
