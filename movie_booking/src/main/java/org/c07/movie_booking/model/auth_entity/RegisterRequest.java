package org.c07.movie_booking.model.auth_entity;

import org.c07.movie_booking.model.Role;

import java.time.LocalDate;


public class RegisterRequest {
    private Long id;
    private String code;

    private String name;

    private String cardId;

    private String email;
    private String password;

    private boolean gender;

    private boolean status;

    private String phoneNumber;
    private String avatar;
    private String address;
    private LocalDate dayOfBirth;
    private Role role;

    public RegisterRequest() {
    }

    public RegisterRequest(Long id, String code, String name, String cardId, String email, String password,
                           boolean gender, boolean status, String phoneNumber, String avatar, String address, Role role, LocalDate dayOfBirth) {
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
        this.dayOfBirth = dayOfBirth;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public LocalDate getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(LocalDate dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }
}
