package org.c07.movie_booking.service;

import org.c07.movie_booking.dto.UserDTO;
import org.c07.movie_booking.dto.UserResponse;
import org.c07.movie_booking.dto.response.UserResDTO;
import org.c07.movie_booking.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;

public interface IUserService {
    UserResponse findUserByEmail(String email);
    //Show List and Search Employee
    Page<UserResDTO> searchEmployees(String valueSearch, Pageable pageable);
    // Remove Employee
    void deleteEmployeeById(Long id);
    // Tìm employee theo id
    UserResDTO findEmployeeById(Long id);
}

