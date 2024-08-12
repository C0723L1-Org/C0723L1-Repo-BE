package org.c07.movie_booking.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
            @RequestBody AuthenticationRequest request,
            HttpServletResponse response
    ){
        String token =authenticationService.authenticate(request);
        Cookie cookie = new Cookie("jwt",token);
        cookie.setHttpOnly(true); // Không cho phép JavaScript truy cập
        cookie.setSecure(true); // Chỉ gửi cookie qua HTTPS
        cookie.setPath("/"); // Áp dụng cho toàn bộ ứng dụng
        response.addCookie(cookie);
        return ResponseEntity.ok(token);
    }
    @GetMapping("/demo")
    public ResponseEntity<?> sayHello(){
        return ResponseEntity.ok("hello");
    }
}
