package org.c07.movie_booking.service.implement;

import org.c07.movie_booking.dto.UserDTO;
import org.c07.movie_booking.dto.UserResponse;
import org.c07.movie_booking.dto.response.UserResDTO;
import org.c07.movie_booking.model.Role;
import org.c07.movie_booking.model.User;
import org.c07.movie_booking.repository.IUserRepository;
import org.c07.movie_booking.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserResponse findUserByEmail(String email) {

        return iUserRepository.findUserByEmail(email);
    }

    //Show List and Search Employee
    @Override
    public Page<UserResDTO> searchEmployees(String valueSearch, Pageable pageable) {
        Page<User> userPage = iUserRepository.SearchEmployees(valueSearch, pageable);
        return userPage.map(this::convertToDTO);
    }
    // Remove Employee
    @Override
    public void deleteEmployeeById(Long id) {
        iUserRepository.deleteEmployeeByQuery(id);
    }
    // Tìm kiếm theo ID
    @Override
    public UserResDTO findEmployeeById(Long id) {
        User user = iUserRepository.findEmployeeById(id).orElse(null);
        if (user == null || user.isStatus()) {
            return null;
        } else {
            return convertToDTO(user);
        }
    }

    @Override
    public User findUserById(Long id) {
        return iUserRepository.findById(id).orElse(null);
    }

    @Override
    public User addNewUser(UserDTO userDTO) {
        if (iUserRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email đã được sử dụng");
        }
        if (iUserRepository.existsByCardId(userDTO.getCardId())){
            throw new IllegalArgumentException("số CCCD đã tồn tại");
        }
        // Thiết lập giá trị mặc định cho userDTO nếu chưa có
        if (userDTO.getCode() == null) {
            userDTO.setCode("KH-" + generateRandomCode());
        }
        if (userDTO.getAvatar() == null) {
            userDTO.setAvatar("https://i.pinimg.com/736x/bc/43/98/bc439871417621836a0eeea768d60944.jpg");
        }
        if (userDTO.getRole() == null) {
            Role defaultRole = new Role();
            defaultRole.setId(1L);
            defaultRole.setName("user");
            userDTO.setRole(defaultRole);
        }
        userDTO.setStatus(false);

        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return iUserRepository.save(user);
    }

    @Override
    public void updateUser(Long id, UserDTO userDTO) {
        User existingUser = iUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Cập nhật các thông tin người dùng
        if (userDTO.getName() != null && !userDTO.getName().isEmpty()) {
            existingUser.setName(userDTO.getName());
        }
        if (userDTO.getGender() != null) {
            existingUser.setGender(userDTO.getGender());
        }
        if (userDTO.getPhoneNumber() != null && !userDTO.getPhoneNumber().isEmpty()) {
            existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        }
        if (userDTO.getAddress() != null && !userDTO.getAddress().isEmpty()) {
            existingUser.setAddress(userDTO.getAddress());
        }
        if (userDTO.getCardId() != null && !userDTO.getCardId().isEmpty()){
            existingUser.setCardId(userDTO.getCardId());
        }
        if (userDTO.getAvatar() != null && !userDTO.getAvatar().isEmpty()) {
            existingUser.setAvatar(userDTO.getAvatar());
        }
        if (userDTO.getDayOfBirth() != null) {
            existingUser.setDayOfBirth(userDTO.getDayOfBirth());
        }

        // Thực hiện lưu thông tin cập nhật vào DB
        iUserRepository.save(existingUser);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return iUserRepository.existsByEmail(email);
    }

    @Override
    public Page<UserDTO> getAllUser(Pageable pageable) {
        Page<User> userPage = iUserRepository.findAll(pageable);
        List<UserDTO> userDTOList = userPage.getContent().stream()
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(user, userDTO);
                    return userDTO;
                })
                .collect(Collectors.toList());

        return new PageImpl<>(userDTOList, pageable, userPage.getTotalElements());
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = iUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public Boolean existsByCardId(String cardId) {
        return iUserRepository.existsByCardId(cardId);
    }

    @Override
    public Boolean existsByPhoneNumber(String phoneNumber) {
        return iUserRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public void changePassword(String email, String currentPassword, String newPassword) {
        // Tìm người dùng theo email
        User user = iUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Kiểm tra mật khẩu cũ
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new IllegalStateException("Mật khẩu cũ không đúng");
        }

        // Kiểm tra mật khẩu mới và mật khẩu xác nhận có khớp nhau không
        if (newPassword == null || newPassword.isEmpty()) {
            throw new IllegalArgumentException("Mật khẩu mới không được để trống");
        }

        // Cập nhật mật khẩu mới
        user.setPassword(passwordEncoder.encode(newPassword));
        iUserRepository.save(user);
    }
    private UserResDTO convertToDTO(User user) {
        UserResDTO userResDTO = modelMapper.map(user, UserResDTO.class);
        if (user.getRole() != null) {
            userResDTO.setRole_id(user.getRole().getId());
        }
        return userResDTO;
    }

    private String generateRandomCode() {
        Random random = new Random();
        int randomNumber = random.nextInt(100000); // Tạo số ngẫu nhiên từ 0 đến 99999
        return String.format("%05d", randomNumber); // Định dạng số với 5 chữ số, thêm số 0 phía trước nếu cần
    }

    // thêm role_id vào UserResDTO

}
