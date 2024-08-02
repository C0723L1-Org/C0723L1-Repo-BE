package org.c07.movie_booking.service.auth;

import lombok.RequiredArgsConstructor;
import org.c07.movie_booking.model.auth_entity.AuthenticationRequest;
import org.c07.movie_booking.model.auth_entity.RegisterRequest;
import org.c07.movie_booking.model.User;
import org.c07.movie_booking.repository.auth.IUserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    IUserDetailRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    public String register(RegisterRequest request) {
        User user = new User(
                request.getCode(),
                request.getName(),
                request.getCardId(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.isGender(),
                request.isStatus(),
                request.getPhoneNumber(),
                request.getAvatar(),
                request.getAddress(),
                request.getRole());
        repository.save(user);
        return jwtService.generateToken(user);
    }

    public String authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = repository.findByEmail(request.getEmail()).orElseThrow();
        return jwtService.generateToken(user);
    }
}
