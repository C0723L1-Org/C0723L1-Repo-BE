package org.c07.movie_booking.dto;

import org.c07.movie_booking.model.Role;

public interface UserResponse {
    Long getId();
    String getName();
    String getCardId();

    String getEmail();
    String getPhoneNumber();
    String getAvatar();
    String getAddress();
    boolean isGender();
    String getRole();
}
