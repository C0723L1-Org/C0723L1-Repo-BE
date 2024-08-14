package org.c07.movie_booking.service;
import org.c07.movie_booking.dto.UserDTO;
import org.c07.movie_booking.dto.UserResponse;
import org.c07.movie_booking.dto.response.UserResDTO;
import org.c07.movie_booking.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface IUserService {
    User createNewUser(UserDTO userDTO);
    User findUserById(Long id);
    User updateUser(User user, Long id);
    Boolean existsByEmail(String email); //email da co trong DB chua
    Page<UserDTO> getAllUser (Pageable pageable);

    UserResponse findUserByEmail(String email);
    //Show List and Search Employee
    Page<UserResDTO> searchEmployees(String valueSearch, Pageable pageable);
    // Remove Employee
    void deleteEmployeeById(Long id);
    // Tìm employee theo id
    UserResDTO findEmployeeById(Long id);
}

