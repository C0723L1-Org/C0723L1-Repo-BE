package org.c07.movie_booking.controller;

import org.c07.movie_booking.model.auth_entity.AuthRequest;
import org.c07.movie_booking.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthenticationController {
    @Autowired
    AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/public/log-in")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        if (authentication.isAuthenticated()){
            return ResponseEntity.ok(authService.authenticate(authRequest));
        } else return new ResponseEntity<>("Error",HttpStatus.BAD_GATEWAY);
    }
}
