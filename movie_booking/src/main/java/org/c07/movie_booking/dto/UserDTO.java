package org.c07.movie_booking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.c07.movie_booking.model.Role;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class UserDTO implements Validator {

    private String code;
    @NotBlank(message = "Vui lòng không để trống")
    private String name;
    @NotBlank(message = "Vui lòng không để trống")
    private String cardId;
    @NotBlank(message = "Vui lòng không để trống")
    @Email(message = "Email không hợp lệ")
    private String email;
    private boolean gender;
    @NotBlank(message = "Vui lòng không để trống")
    private String phoneNumber;
    private String avatar;
    @NotBlank(message = "Vui lòng không để trống")
    private String address;
    @NotBlank
    private String password;

    private Set<String> roles;

    public UserDTO() {
        // Thiết lập giá trị mặc định cho avatar
        this.avatar = "https://i.pinimg.com/736x/bc/43/98/bc439871417621836a0eeea768d60944.jpg";
        // Thiết lập giá trị mặc định cho code
        this.code = "KH-" + generateRandomCode();
        // Thiết lập giá trị mặc định cho roles
        this.roles = new HashSet<>();
        this.roles.add("USER");
    }

    private String generateRandomCode() {
        Random random = new Random();
        int randomNumber = random.nextInt(100000);
        return String.format("%05d", randomNumber);
    }

    // Getters and setters for all fields

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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // Custom validation logic if any
    }
}
