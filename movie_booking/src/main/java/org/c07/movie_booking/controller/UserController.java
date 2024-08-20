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
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private IUserService iUserService;


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
}
