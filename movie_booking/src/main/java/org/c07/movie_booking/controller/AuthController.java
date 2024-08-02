package org.c07.movie_booking.controller;

import lombok.RequiredArgsConstructor;

import org.c07.movie_booking.model.auth_entity.AuthenticationRequest;
import org.c07.movie_booking.model.auth_entity.RegisterRequest;
import org.c07.movie_booking.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("public/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("public/authenticate")
    public ResponseEntity<?> register(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @GetMapping("/demo")
    public ResponseEntity<?> sayHello(){
        return ResponseEntity.ok("hello");
    }
}
