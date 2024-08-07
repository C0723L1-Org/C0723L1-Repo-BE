package org.c07.movie_booking.controller;

import jakarta.validation.Valid;
import org.c07.movie_booking.dto.UserDTO;
import org.c07.movie_booking.service.IRoleService;
import org.c07.movie_booking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping("/public/register")
    public ResponseEntity<?> addNewUser(@Valid @RequestBody UserDTO userDTO,
                                        BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes) {
        new UserDTO().validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            // Chuyển hướng trở lại form đăng ký khi có lỗi
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("user", userDTO);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (iUserService.existsByEmail(userDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already in use");
        }


        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        iUserService.addNewUser(userDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully!");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("check-email")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        boolean exists = iUserService.existsByEmail(email);
        return ResponseEntity.ok(new HashMap<String, Boolean>() {{
            put("exists", exists);
        }});
    }
    @GetMapping("/public/list")
    public ResponseEntity<Page<UserDTO>> listUsers(Pageable pageable) {
        Page<UserDTO> userPage = iUserService.getAllUser(pageable);
        return ResponseEntity.ok(userPage);
    }
    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile(Principal principal) {
        String email = principal.getName();
        UserDTO userDTO = iUserService.findUserByEmail(email);
        return ResponseEntity.ok(userDTO);
    }


}
