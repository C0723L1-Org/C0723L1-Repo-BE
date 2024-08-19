package org.c07.movie_booking.model.auth_entity;

public class BookingRequest {
    Long seatId;
    Long showtimeId;

    public BookingRequest() {
    }

    public BookingRequest(Long seatId, Long showtimeId) {
        this.seatId = seatId;
        this.showtimeId = showtimeId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public Long getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(Long showtimeId) {
        this.showtimeId = showtimeId;
    }
}
