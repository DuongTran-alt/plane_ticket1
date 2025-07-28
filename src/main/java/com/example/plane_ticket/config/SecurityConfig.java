package com.example.plane_ticket.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


//Tạo class này để tự customize cho các bước bảo mật mà mình muốn
//Tạo annotaiton này để báo spring biết đây là configuration class và có thể tìm configuaration ở đây
@Configuration
//Báo để spring biết sẽ không đi qua cái defaut security configuration mà sẽ lấy ở đây
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    //Thong bao cho spring day la filterchain can dung de configuaration theo
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //Disable csrf token
        //Ngan moi nguoi co the truy cap neu chua dang nhap
        //Tao form dang nhap de authorize theo defautl
        //Tao moi session id voi moi request
        //Co the tra ve SecurityFilterChain
        return http.csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request
                        //Cho phep tu do dang ki khong can dang nhap
                        .requestMatchers("/register").permitAll()
                        .anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    //De co the connect voi database
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);


        return provider;
    }
}
