package com.example.plane_ticket.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private int id;
    private String type;

    @JsonIgnore
    @OneToOne (mappedBy = "seat")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn (name = "plane_id")
    private Plane plane;
}
