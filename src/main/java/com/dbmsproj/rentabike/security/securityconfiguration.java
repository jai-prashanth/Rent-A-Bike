package com.dbmsproj.rentabike.security;

import com.dbmsproj.rentabike.Service.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance;


@Configuration
@EnableWebSecurity
public class securityconfiguration {
    private userservice userservices;


    @Autowired
    securityconfiguration(userservice userservices) {
        this.userservices = userservices;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userService;
        auth.userDetailsService(userservices).passwordEncoder(getInstance());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
                                .requestMatchers("/", "/home", "/register", "login", "logout", "/availableBikes", "/images/*", "/bikes/*", "/addBike", "/bikes","/homeUser","/profile", "/admin","/payment","/bookings","/createBooking", "/about","/contact","/help","/privacy","/terms").permitAll()
                                .requestMatchers("/addBike").hasRole("ADMIN")
                                .anyRequest().authenticated()
//                        .requestMatchers("/signin", "/signup","/login","/css/login.css", "/register","/css/*","/js/*","/pics/*").permitAll()
//                        .requestMatchers("/addBike").hasRole("ADMIN")
//                        .anyRequest().authenticated()
                )
                .formLogin((formLogin) -> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/homeUser", true)
                        .failureForwardUrl("/login")
//                        .failureHandler((httpServletRequest, httpServletResponse, authenticationException) -> {
//                            if (authenticationException instanceof UsernameNotFoundException) {
//                                // Display an alert to the user that no user exists with the username
//                                httpServletRequest.setAttribute("alertMessage", "No user exists with the username: " + authenticationException.getMessage());
//                                httpServletRequest.getRequestDispatcher("/login").forward(httpServletRequest, httpServletResponse);
//                            } else {
//                                httpServletResponse.sendRedirect("/login");
//                            }
//                        })
                        .permitAll()
                )
                .logout((logout) ->
                        logout.deleteCookies("remove")
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/").permitAll()
                )
                .httpBasic(withDefaults());
        return http.csrf(AbstractHttpConfigurer::disable).build();
    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}