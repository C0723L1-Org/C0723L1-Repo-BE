package org.c07.movie_booking.service.implement;


import org.c07.movie_booking.dto.ChangePasswordRequest;
import org.c07.movie_booking.dto.UserDTO;
import org.c07.movie_booking.dto.UserResponse;
import org.c07.movie_booking.model.Role;
import org.c07.movie_booking.model.User;
import org.c07.movie_booking.repository.IUserRepositoty;
import org.c07.movie_booking.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepositoty iUserRepositoty;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User addNewUser(UserDTO userDTO) {
        if (iUserRepositoty.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email đã được sử dụng");
        }
        if (iUserRepositoty.existsByCardId(userDTO.getCardId())){
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
        return iUserRepositoty.save(user);
    }

    // Hàm tạo mã ngẫu nhiên 5 chữ số không âm
    private String generateRandomCode() {
        Random random = new Random();
        int randomNumber = random.nextInt(100000); // Tạo số ngẫu nhiên từ 0 đến 99999
        return String.format("%05d", randomNumber); // Định dạng số với 5 chữ số, thêm số 0 phía trước nếu cần
    }

    // Lấy thông tin người dùng theo id

    @Override
    public void updateUser(Long id, UserDTO userDTO) {
        User existingUser = iUserRepositoty.findById(id)
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
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        // Thực hiện lưu thông tin cập nhật vào DB
        iUserRepositoty.save(existingUser);
    }



    @Override
    public Boolean existsByEmail(String email) {
        return iUserRepositoty.existsByEmail(email);
    }


    @Override
    public Page<UserDTO> getAllUser(Pageable pageable) {
        Page<User> userPage = iUserRepositoty.findAll(pageable);
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
        User user = iUserRepositoty.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public Boolean existsByCardId(String cardId) {
        return iUserRepositoty.existsByCardId(cardId);
    }

    @Override
    public Boolean existsByPhoneNumber(String phoneNumber) {
        return iUserRepositoty.existsByPhoneNumber(phoneNumber);
    }
    public UserResponse findUserByEmail(String email) {

        return iUserRepositoty.findUserByEmail(email);
    }

    @Override
    public void changePassword(String email, String oldPassword, String newPassword) {
        // Tìm người dùng theo email
        User user = iUserRepositoty.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Kiểm tra mật khẩu cũ
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalStateException("Mật khẩu cũ không đúng");
        }

        // Kiểm tra mật khẩu mới và mật khẩu xác nhận có khớp nhau không
        if (newPassword == null || newPassword.isEmpty()) {
            throw new IllegalArgumentException("Mật khẩu mới không được để trống");
        }

        // Cập nhật mật khẩu mới
        user.setPassword(passwordEncoder.encode(newPassword));
        iUserRepositoty.save(user);
    }


}
