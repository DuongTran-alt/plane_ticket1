package com.example.plane_ticket.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String homePage(HttpServletRequest request){
        return "Welcome to our page! " + request.getSession().getId();
    }

    //Can csrf-token de lam cac thao tac nhu put post d
    @GetMapping("Csrf-Token")
    public CsrfToken getCsrfToken (HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
