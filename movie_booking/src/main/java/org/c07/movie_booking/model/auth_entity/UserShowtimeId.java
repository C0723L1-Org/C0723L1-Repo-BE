package org.c07.movie_booking.model.auth_entity;

public class UserShowtimeId {
    private Long userId;
    private Long showtimeId;

    public UserShowtimeId() {
    }

    public UserShowtimeId(Long userId, Long showtimeId) {
        this.userId = userId;
        this.showtimeId = showtimeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(Long showtimeId) {
        this.showtimeId = showtimeId;
    }
}
