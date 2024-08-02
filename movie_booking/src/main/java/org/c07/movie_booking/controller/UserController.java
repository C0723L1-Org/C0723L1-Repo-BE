package org.c07.movie_booking.controller;

import jakarta.validation.Valid;
import org.c07.movie_booking.dto.request.UserReqDTO;
import org.c07.movie_booking.dto.response.UserResDTO;
import org.c07.movie_booking.service.IUserService;
import org.c07.movie_booking.service.implement.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api/v1/user"})
public class UserController {
    @Autowired
    IUserService iUserService;
    @Autowired
    private UserService userService;
    //Show List and Search Employee
    @GetMapping("/private/list-employee")
    public ResponseEntity<Page<UserResDTO>> searchEmployees(
            @RequestParam(value = "valueSearch", defaultValue = "") String valueSearch,
            @RequestParam("page") Optional<Integer> page) {

        System.out.println("Search criteria:");
        System.out.println("Name: " + valueSearch);

        int currentPage = page.map(p -> Math.max(p, 0)).orElse(0);
        Pageable pageable = PageRequest.of(currentPage, 5);
        Page<UserResDTO> employees = iUserService.searchEmployees(
                valueSearch, pageable);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    // Remove Employee
    @PutMapping("/private/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        UserResDTO userResDTO = iUserService.findEmployeeById(id);
        if (userResDTO == null) {
            return new ResponseEntity<>("Employee not found to delete !", HttpStatus.BAD_REQUEST);
        }
        iUserService.deleteEmployeeById(id);
        return new ResponseEntity<>("Delete employee successfully.", HttpStatus.OK);
    }
    @PostMapping("/private/create")
    public ResponseEntity<String> createEmployee(@Valid @RequestBody UserReqDTO userDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        new UserReqDTO().validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }

        List<String> allEmailExist = userService.findAllExistEmail();
        if (allEmailExist.contains(userDTO.getEmail())) {
            return new ResponseEntity<>("User email exists!", HttpStatus.BAD_REQUEST);
        }

        userService.createEmployee(userDTO);
        return new ResponseEntity<>("User created successfully!", HttpStatus.CREATED);
    }

    @PutMapping("/private/update")
    public ResponseEntity<String> updateEmployee(@Valid @RequestBody UserReqDTO userDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        new UserReqDTO().validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }

        List<String> allEmailExist = userService.findAllExistEmail();
        if (allEmailExist.contains(userDTO.getEmail())) {
            return new ResponseEntity<>("User email exists!", HttpStatus.BAD_REQUEST);
        }

        userService.updateEmployee(userDTO);
        return new ResponseEntity<>("User updated successfully!", HttpStatus.OK);
    }
}
