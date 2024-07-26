package org.c07.movie_booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int idMovie;
    private int idRoom;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate showDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime startTime ;

    public Showtime() {
    }

    public Showtime(long id, int idMovie, int idRoom, LocalDate showDate, LocalDateTime startTime) {
        this.id = id;
        this.idMovie = idMovie;
        this.idRoom = idRoom;
        this.showDate = showDate;
        this.startTime = startTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
