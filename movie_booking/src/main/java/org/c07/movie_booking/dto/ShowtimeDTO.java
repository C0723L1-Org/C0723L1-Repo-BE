package org.c07.movie_booking.dto;

import jakarta.validation.constraints.NotBlank;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.model.Room;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ShowtimeDTO implements Validator {
    private long id;
    private String nameMovie;
    private String roomName;
    @NotBlank(message = "Ngày Khởi chiếu không được bỏ trống")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate showDate;
    @NotBlank(message = "Thời gian khởi chiếu không được bỏ trống")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime ;
    private Room room;
    private Movie moviel;

    public ShowtimeDTO() {
    }

    public ShowtimeDTO(LocalDate showDate, LocalTime startTime, Room room, Movie moviel) {
        this.showDate = showDate;
        this.startTime = startTime;
        this.room = room;
        this.moviel = moviel;
    }

    public ShowtimeDTO(long id, String nameMovie, String roomName, LocalDate showDate, LocalTime startTime) {
        this.id = id;
        this.nameMovie = nameMovie;
        this.roomName = roomName;
        this.showDate = showDate;
        this.startTime = startTime;
    }

    public ShowtimeDTO(String nameMovie, String roomName, LocalDate showDate, LocalTime startTime) {
        this.nameMovie = nameMovie;
        this.roomName = roomName;
        this.showDate = showDate;
        this.startTime = startTime;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Movie getMoviel() {
        return moviel;
    }

    public void setMoviel(Movie moviel) {
        this.moviel = moviel;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
