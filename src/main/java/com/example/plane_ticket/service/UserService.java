package com.example.plane_ticket.service;

import com.example.plane_ticket.module.Seat;
import com.example.plane_ticket.repo.SeatRepo;
import com.example.plane_ticket.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;
}

