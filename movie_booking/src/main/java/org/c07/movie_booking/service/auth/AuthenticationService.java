//package org.c07.movie_booking.service.auth;
//
//import org.c07.movie_booking.model.auth_entity.AuthenticationRequest;
//import org.c07.movie_booking.model.auth_entity.RegisterRequest;
//import org.c07.movie_booking.model.User;
//import org.c07.movie_booking.repository.auth.IUserDetailRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class AuthenticationService {
//    @Autowired
//    IUserDetailRepository repository;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//    @Autowired
//    JwtService jwtService;
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//
//    public String authenticate(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//        );
//        User user = repository.findByEmail(request.getEmail()).orElseThrow();
//
//        return jwtService.generateToken(user);
//    }
//}