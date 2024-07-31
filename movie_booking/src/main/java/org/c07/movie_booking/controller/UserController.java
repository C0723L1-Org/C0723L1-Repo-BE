package org.c07.movie_booking.controller;

import jakarta.validation.Valid;
import org.c07.movie_booking.dto.ResponMessage;
import org.c07.movie_booking.dto.UserDTO;
import org.c07.movie_booking.model.Role;
import org.c07.movie_booking.model.User;
import org.c07.movie_booking.service.IRoleService;
import org.c07.movie_booking.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;


@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/public/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO){
        if(iUserService.existsByEmail(userDTO.getEmail())){
            return new ResponseEntity<>(new ResponMessage("Email đã tồn tại"), HttpStatus.OK);
        }

        // Mã hóa mật khẩu 11
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(encodedPassword);
        user.setCardId(userDTO.getCardId());
        user.setAddress(userDTO.getAddress());
        user.setGender(userDTO.isGender());

        // Xử lý Role
        Role role = iRoleService.findAll().stream()
                .filter(r -> r.getName().equals("USER"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);

        iUserService.createNewUser(userDTO);
        return new ResponseEntity<>(new ResponMessage("Đăng ký thành công"), HttpStatus.OK);
    }

    @PutMapping("/profile/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        User existingUser = iUserService.findUserById(id);
        if (existingUser == null) {
            return new ResponseEntity<>(new ResponMessage("Người dùng không tồn tại"), HttpStatus.NOT_FOUND);
        }

        // Cập nhật thông tin người dùng từ UserDTO
        BeanUtils.copyProperties(userDTO, existingUser, "id");

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        Role role = iRoleService.findAll().stream()
                .filter(r -> r.getName().equals("USER"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Role not found"));
        existingUser.setRole(role);

        iUserService.updateUser(existingUser, id);

        return new ResponseEntity<>(new ResponMessage("Cập nhật thông tin người dùng thành công"), HttpStatus.OK);
    }

    @GetMapping("/public/check-email")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        boolean exists = iUserService.existsByEmail(email);
        return ResponseEntity.ok(new HashMap<String, Boolean>() {{
            put("exists", exists);
        }});
    }

    @GetMapping("/list")
    public ResponseEntity<Page<UserDTO>> listUsers(Pageable pageable) {
        Page<UserDTO> userPage = iUserService.getAllUser(pageable);
        return ResponseEntity.ok(userPage);
    }
}
