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
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository iUserRepositoty;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public User createNewUser(UserDTO userDTO) {
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
            defaultRole.setName("ROLE_USER");
            userDTO.setRole(defaultRole);
        }

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
    public User updateUser(User user, Long id) {
        User existingUser = iUserRepositoty.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setCode(user.getCode());
        existingUser.setName(user.getName());
        existingUser.setCardId(user.getCardId());
        existingUser.setEmail(user.getEmail());
        existingUser.setGender(user.isGender());
        existingUser.setStatus(user.isStatus());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setAvatar(user.getAvatar());
        existingUser.setRole(user.getRole());
        existingUser.setAddress(user.getAddress());
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
    public UserResponse findUserByEmail(String email) {

        return iUserRepositoty.findUserByEmail(email);
    }

    //Show List and Search Employee
    @Override
    public Page<UserResDTO> searchEmployees(String valueSearch, Pageable pageable) {
        Page<User> userPage = iUserRepositoty.SearchEmployees(valueSearch, pageable);
        return userPage.map(this::convertToDTO);
    }
    // Remove Employee
    @Override
    public void deleteEmployeeById(Long id) {
        iUserRepositoty.deleteEmployeeByQuery(id);
    }
    // Tìm kiếm theo ID
    @Override
    public UserResDTO findEmployeeById(Long id) {
        User user = iUserRepositoty.findEmployeeById(id).orElse(null);
        if (user == null || user.isStatus()) {
            return null;
        } else {
            return convertToDTO(user);
        }
    }
    // thêm role_id vào UserResDTO
    private UserResDTO convertToDTO(User user) {
        UserResDTO userResDTO = modelMapper.map(user, UserResDTO.class);
        if (user.getRole() != null) {
            userResDTO.setRole_id(user.getRole().getId());
        }
        return userResDTO;
    }
}
