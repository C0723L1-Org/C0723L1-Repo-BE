package org.c07.movie_booking.controller;

import jakarta.validation.Valid;
import org.c07.movie_booking.dto.ChangePasswordRequest;
import org.c07.movie_booking.dto.UserDTO;
import org.c07.movie_booking.dto.response.UserResDTO;
import java.security.Principal;
import org.c07.movie_booking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("private/list-employee")
    public ResponseEntity<Page<UserResDTO>> searchEmployees(
            @RequestParam(value = "valueSearch", defaultValue = "") String valueSearch,
            @RequestParam("page") Optional<Integer> page) {

        System.out.println("Search criteria:");
        System.out.println("Name: " + valueSearch);
        int currentPage = page.map(p -> Math.max(p, 0)).orElse(0);
        Pageable pageable = PageRequest.of(currentPage, 7);
        Page<UserResDTO> employees = iUserService.searchEmployees(
                valueSearch, pageable);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Remove Employee
    @PutMapping("private/delete-employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        UserResDTO userResDTO = iUserService.findEmployeeById(id);
        if (userResDTO == null) {
            return new ResponseEntity<>("Employee not found to delete !", HttpStatus.BAD_REQUEST);
        }
        iUserService.deleteEmployeeById(id);
        return new ResponseEntity<>("Delete employee successfully.", HttpStatus.OK);
    }
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, Principal principal) {
        try {
            iUserService.changePassword(
                    principal.getName(), // Email từ principal (người dùng hiện tại)
                    changePasswordRequest.getCurrentPassword(),
                    changePasswordRequest.getNewPassword()
            );
            return ResponseEntity.ok("Password changed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
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
        if (iUserService.existsByCardId(userDTO.getCardId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID Card already in use");
        }
        if (iUserService.existsByPhoneNumber(userDTO.getPhoneNumber())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Number phone already in use");
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
    @GetMapping("/public/profile")
    public ResponseEntity<UserDTO> getUserProfile(Principal principal) {
        String email = principal.getName();
        UserDTO userDTO = iUserService.findByEmail(email);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        iUserService.updateUser(id, userDTO);
        return ResponseEntity.ok("User updated successfully");
    }
}
