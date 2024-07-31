package org.c07.movie_booking.dto.request;

import jakarta.validation.constraints.*;
import org.springframework.validation.Errors;

import java.util.Date;

public class UserReqDTO {
    private Long id;

    @NotBlank(message = "Code is mandatory")
    @Pattern(regexp = "^NV\\d+$", message = "Code must start with 'NV' followed by digits")
    private String code;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Card ID is mandatory")
    private String cardId;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotNull(message = "Gender is mandatory")
    private Boolean gender;

    @NotNull(message = "Status is mandatory")
    private Boolean status;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^0\\d{9}$", message = "Phone number must start with 0 and have 10 digits")
    private String phoneNumber;

    @NotBlank(message = "Avatar is mandatory")
    private String avatar;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must have at least 8 characters")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[^\\w\\d\\s:]).+$", message = "Password must contain a letter, a number and a special character")
    private String password;

    @NotNull(message = "Birthday is mandatory")
    @PastOrPresent(message = "Birthday must be a past or present date")
    private Date birthday;

    @NotNull(message = "Role ID is mandatory")
    private Long roleId;

    public UserReqDTO() {
    }


    public UserReqDTO(Long id, String code, String name, String cardId, String email, Boolean gender, Boolean status, String phoneNumber, String avatar, String address, String password, Date birthday, Long roleId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.cardId = cardId;
        this.email = email;
        this.gender = gender;
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.address = address;
        this.password = password;
        this.birthday = birthday;
        this.roleId = roleId;
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

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public void validate(Object target, Errors errors) {
        UserReqDTO userReqDTO = (UserReqDTO) target;


    }
}
