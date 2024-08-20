package org.c07.movie_booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.c07.movie_booking.model.BookingStatus;
import org.c07.movie_booking.model.Seat;
import org.c07.movie_booking.model.Showtime;
import org.c07.movie_booking.model.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class BookingDto implements Validator {
    private long id;
    private String codeBooking;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateBooking;
    @Min(0)
    private double totalAmount;
    private BookingStatus bookingStatus;
//    @NotNull(message ="Please Login")
    private User user;
    @NotNull(message = "Please choose showtime")
    private Showtime showTime;
    @NotNull(message = "Please choose seat")
    private Seat seat;

    public BookingDto() {
    }

    public BookingDto(String codeBooking, LocalDate dateBooking, double totalAmount, BookingStatus bookingStatus, User user, Showtime showTime, Seat seat) {
        this.codeBooking = codeBooking;
        this.dateBooking = dateBooking;
        this.totalAmount = totalAmount;
        this.bookingStatus = bookingStatus;
        this.user = user;
        this.showTime = showTime;
        this.seat = seat;
    }

    public String getCodeBooking() {
        return codeBooking;
    }

    public void setCodeBooking(String codeBooking) {
        this.codeBooking = codeBooking;
    }

    public LocalDate getDateBooking() {
        return dateBooking;
    }

    public void setDateBooking(LocalDate dateBooking) {
        this.dateBooking = dateBooking;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Showtime getShowTime() {
        return showTime;
    }

    public void setShowTime(Showtime showTime) {
        this.showTime = showTime;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookingDto bookingDto = (BookingDto) target;
        if(bookingDto.getTotalAmount() <0){
            errors.rejectValue("totalAmount","Total Amount must be greater than 0");
        }
    }
}
