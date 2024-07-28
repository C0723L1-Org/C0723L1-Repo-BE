package org.c07.movie_booking.controller;

import org.c07.movie_booking.configuration.AuthenticationResponse;
import org.c07.movie_booking.configuration.AuthenticationRequest;
import org.c07.movie_booking.configuration.IAuthenticationService;
import org.c07.movie_booking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthenticationController {
    @Autowired
    private  IAuthenticationService service;
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }
}
