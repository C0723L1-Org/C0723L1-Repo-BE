package org.c07.movie_booking.service.implement;


import org.c07.movie_booking.dto.UserDTO;
import org.c07.movie_booking.model.Role;
import org.c07.movie_booking.model.User;
import org.c07.movie_booking.repository.IUserRepositoty;
import org.c07.movie_booking.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepositoty iUserRepositoty;


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
    public User findUserById(Long id) {
        return iUserRepositoty.findById(id)
                .orElseThrow(() -> new RuntimeException("Người dùng không được tìm thấy"));
    }

    // Cập nhật thông tin
    @Override
    public User updateUser(UserDTO userDTO, String email) {
        User existingUser = iUserRepositoty.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BeanUtils.copyProperties(userDTO, existingUser, "id", "email", "code", "status");
        return iUserRepositoty.save(existingUser);
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
    public UserDTO findUserByEmail(String email) {
        User user = iUserRepositoty.findUserByEmail(email)
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
}
