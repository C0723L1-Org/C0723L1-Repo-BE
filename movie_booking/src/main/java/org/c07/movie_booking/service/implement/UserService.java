package org.c07.movie_booking.service.implement;

import org.c07.movie_booking.dto.UserDTO;
import org.c07.movie_booking.model.Role;
import org.c07.movie_booking.model.User;
import org.c07.movie_booking.repository.IRoleRepository;
import org.c07.movie_booking.repository.IUserRepositoty;
import org.c07.movie_booking.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepositoty iUserRepositoty;

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public User createNewUser(UserDTO userDTO) {
        // Thiết lập giá trị mặc định cho userDTO nếu chưa có
        if (userDTO.getCode() == null) {
            userDTO.setCode("KH-" + generateRandomCode());
        }
        if (userDTO.getAvatar() == null) {
            userDTO.setAvatar("https://i.pinimg.com/736x/bc/43/98/bc439871417621836a0eeea768d60944.jpg");
        }

        User user = new User();
        BeanUtils.copyProperties(userDTO, user);

        // Set role
        Role role = roleRepository.findRoleByName("USER").orElseThrow(
                () -> new RuntimeException("Default role not found")
        );
        user.setRole(role);

        return iUserRepositoty.save(user);
    }

    private String generateRandomCode() {
        Random random = new Random();
        int randomNumber = random.nextInt(100000); // Tạo số ngẫu nhiên từ 0 đến 99999
        return String.format("%05d", randomNumber); // Định dạng số với 5 chữ số, thêm số 0 phía trước nếu cần
    }

    @Override
    public User findUserById(Long id) {
        return iUserRepositoty.findById(id)
                .orElseThrow(() -> new RuntimeException("Người dùng không được tìm thấy"));
    }

    @Override
    public User updateUser(User user, Long id) {
        User existingUser = iUserRepositoty.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(user.getName());
        existingUser.setCardId(user.getCardId());
        existingUser.setEmail(user.getEmail());
        existingUser.setGender(user.isGender());
        existingUser.setStatus(user.isStatus());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setAvatar(user.getAvatar());
        existingUser.setAddress(user.getAddress());

        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(user.getPassword());
        }

        Role role = user.getRole();
        if (role != null) {
            existingUser.setRole(role);
        }

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
    public boolean existsByUsername(String username) {
        return false;
    }
}
