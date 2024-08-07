package org.c07.movie_booking.service;



import org.c07.movie_booking.dto.UserDTO;
import org.c07.movie_booking.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    User addNewUser(UserDTO userDTO);
    User findUserById(Long id);
    User updateUser(UserDTO userDTO, String email);
    Boolean existsByEmail(String email); //email da co trong DB chua
    Page<UserDTO> getAllUser (Pageable pageable);

    UserDTO findUserByEmail (String email);

}
