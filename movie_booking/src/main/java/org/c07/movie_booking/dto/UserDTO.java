package org.c07.movie_booking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.c07.movie_booking.model.Role;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.Random;

public class UserDTO implements Validator {


    private Long id;
    @NotBlank(message = "Vui lòng không để trống")
    private String code;
    @NotBlank(message = "Vui lòng không để trống")
    private String name;
    @NotBlank(message = "Vui lòng không để trống")
    private String cardId;
    @NotBlank(message = "Vui lòng không để trống")
    @Email(message = "Email không hợp lệ")
    private String email;
    @NotBlank(message = "Vui lòng không để trống")
    private String password;
    private boolean gender;
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
    }

    private String generateRandomCode() {
        Random random = new Random();
        int randomNumber = random.nextInt(100000);
        return String.format("%05d", randomNumber);
    }


    public UserDTO(Long id, String code, String name, String cardId, String email, String password, boolean gender, boolean status, String phoneNumber, String avatar, String address, Role role) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.cardId = cardId;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.address = address;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
