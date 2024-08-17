//package org.c07.movie_booking.service.implement;
//
//import org.c07.movie_booking.dto.UserResponse;
//import org.c07.movie_booking.dto.response.UserResDTO;
//import org.c07.movie_booking.model.User;
//import org.c07.movie_booking.repository.IUserRepository;
//import org.c07.movie_booking.service.IUserService;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService implements IUserService {
//    @Autowired
//    private IUserRepository iUserRepository;
//    @Autowired
//    ModelMapper modelMapper;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//
//    public UserResponse findUserByEmail(String email) {
//
//        return iUserRepository.findUserByEmail(email);
//    }
//
//    //Show List and Search Employee
//    @Override
//    public Page<UserResDTO> searchEmployees(String valueSearch, Pageable pageable) {
//        Page<User> userPage = iUserRepository.SearchEmployees(valueSearch, pageable);
//        return userPage.map(this::convertToDTO);
//    }
//    // Remove Employee
//    @Override
//    public void deleteEmployeeById(Long id) {
//        iUserRepository.deleteEmployeeByQuery(id);
//    }
//    // Tìm kiếm theo ID
//    @Override
//    public UserResDTO findEmployeeById(Long id) {
//        User user = iUserRepository.findEmployeeById(id).orElse(null);
//        if (user == null || user.isStatus()) {
//            return null;
//        } else {
//            return convertToDTO(user);
//        }
//    }
//    // thêm role_id vào UserResDTO
//    private UserResDTO convertToDTO(User user) {
//        UserResDTO userResDTO = modelMapper.map(user, UserResDTO.class);
//        if (user.getRole() != null) {
//            userResDTO.setRole_id(user.getRole().getId());
//        }
//        return userResDTO;
//    }
//
//
//}
