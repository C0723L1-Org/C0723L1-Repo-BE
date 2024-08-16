package org.c07.movie_booking.service;

import org.c07.movie_booking.model.Showtime;

import java.util.List;

public interface IShowtimeService {
    List<Showtime> findShowtimeByIdMovie(Long id,String date,String time);
}
