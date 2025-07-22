package com.example.plane_ticket.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


//Tạo class này để tự customize cho các bước bảo mật mà mình muốn
//Tạo annotaiton này để báo spring biết đây là configuration class và có thể tìm configuaration ở đây
@Configuration
//Báo để spring biết sẽ không đi qua cái defaut security configuration mà sẽ lấy ở đây
@EnableWebSecurity
public class SecurityConfig {

    //Thong bao cho spring day la filterchain can dung de configuaration theo
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //Disable csrf token
        http.csrf(customizer -> customizer.disable());
        //Ngan moi nguoi co the truy cap neu chua dang nhap
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        //Tao form dang nhap de authorize theo defautl
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        //Tao moi session id voi moi request
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //Co the tra ve SecurityFilterChain
        return http.build();
    }
}
