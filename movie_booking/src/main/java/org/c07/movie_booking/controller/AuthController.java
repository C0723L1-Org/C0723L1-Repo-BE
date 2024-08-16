package org.c07.movie_booking.controller;

import jakarta.servlet.http.Cookie;


import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.c07.movie_booking.dto.UserResponse;
import org.c07.movie_booking.model.User;
import org.c07.movie_booking.model.auth_entity.AuthenticationRequest;
import org.c07.movie_booking.model.auth_entity.RegisterRequest;
import org.c07.movie_booking.service.IUserService;
import org.c07.movie_booking.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    IUserService iUserService;

    @PostMapping("public/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("public/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationRequest request,
            HttpServletResponse response
    ){
        String token =authenticationService.authenticate(request);
        Cookie cookie = new Cookie("jwt",token);


        cookie.setMaxAge(30*60); // set thời gian sống của cookie

        cookie.setHttpOnly(true); // Không cho phép JavaScript truy cập
        cookie.setSecure(true); // Chỉ gửi cookie qua HTTPS
        cookie.setPath("/"); // Áp dụng cho toàn bộ ứng dụng
        response.addCookie(cookie);
        return ResponseEntity.ok(token);
    }
    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(Principal principal){
        String email =  principal.getName();
        UserResponse user = iUserService.findUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
    @PostMapping("/log-out")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response){
        // Tạo lại cookie jwt với giá trị rỗng và maxAge bằng 0 để xóa nó
        Cookie jwtCookie = new Cookie("jwt", null);
        jwtCookie.setPath("/");
//        jwtCookie.setHttpOnly(true);
        jwtCookie.setMaxAge(0); // Đặt maxAge là 0 để xóa cookie
        // Thêm cookie vào phản hồi
        response.addCookie(jwtCookie);
        return ResponseEntity.ok("Đăng xuất thành công");
    }
}
