package org.c07.movie_booking.service;

import org.c07.movie_booking.dto.ShowtimeDTO;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.model.Room;
import org.c07.movie_booking.model.Showtime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface IShowtimeService {
    List<ShowtimeDTO> findAllShowtime();
    void create(LocalDate showDate, LocalTime startTime, Room room, Movie movie);
}
