package org.c07.movie_booking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.c07.movie_booking.model.Role;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.Random;

@Getter
@Setter
public class UserDTO implements Validator {
    private Long id;
    private String code;

    @NotBlank(message = "Vui lòng không để trống")
    private String name;

    @NotBlank(message = "Vui lòng không để trống")
    private String cardId;

    @NotNull(message = "Vui lòng không để trống")
    private LocalDate dayOfBirth;

    @NotNull(message = "Vui lòng không để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Vui lòng không để trống")
    private String password;

    private Boolean gender;
    private boolean status;

    @NotBlank(message = "Vui lòng không để trống")
    private String phoneNumber;

    private String avatar;

    @NotBlank(message = "Vui lòng không để trống")
    private String address;

    private Role role;

    public UserDTO() {
        // Thiết lập giá trị mặc định cho avatar
        this.avatar = "https://i.pinimg.com/736x/bc/43/98/bc439871417621836a0eeea768d60944.jpg";
        // Thiết lập giá trị mặc định cho code
        this.code = "KH-" + generateRandomCode();
        // Thiết lập giá trị mặc định cho role
        this.role = new Role(1L, "Customer");
        this.status = false;
    }

    private String generateRandomCode() {
        Random random = new Random();
        int randomNumber = random.nextInt(100000);
        return String.format("%05d", randomNumber);
    }

    public UserDTO(Long id, String code, String name, String cardId, LocalDate dayOfBirth, String email, String password, Boolean gender, boolean status, String phoneNumber, String avatar, String address, Role role) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.cardId = cardId;
        this.dayOfBirth = dayOfBirth;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.address = address;
        this.role = role;
    }

    public UserDTO(String code, String name, String cardId, LocalDate dayOfBirth, String email, String password, Boolean gender, boolean status, String phoneNumber, String avatar, String address, Role role) {
        this.code = code;
        this.name = name;
        this.cardId = cardId;
        this.dayOfBirth = dayOfBirth;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.address = address;
        this.role = role;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // Bạn có thể thêm logic kiểm tra tùy chỉnh tại đây
        // Ví dụ: kiểm tra ngày sinh không ở tương lai
        UserDTO userDTO = (UserDTO) target;
        if (userDTO.getDayOfBirth() != null && userDTO.getDayOfBirth().isAfter(LocalDate.now())) {
            errors.rejectValue("dayOfBirth", "dayOfBirth.future", "Ngày sinh không thể ở tương lai");
        }
    }
}
