package com.example.plane_ticket.module;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private int id;
    @JsonFormat (shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy HH:mm")
    private Date startTime;
    private double price;

    @OneToOne
    @JoinColumn (name = "seat_id")
    private Seat seat;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "booking_email")
    private Booking booking;
}
