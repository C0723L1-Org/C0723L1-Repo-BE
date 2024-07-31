package org.c07.movie_booking.service.implement;

import org.c07.movie_booking.dto.ShowtimeDTO;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.model.Room;
import org.c07.movie_booking.model.Showtime;
import org.c07.movie_booking.repository.IShowtimeRepository;
import org.c07.movie_booking.service.IShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
@Service
public class ShowtimeService implements IShowtimeService {
    @Autowired
     private  IShowtimeRepository iShowtimeRepository;
    @Override
    public List<ShowtimeDTO> findAllShowtime() {
        return iShowtimeRepository.findAllShowTime();
    }

    @Override
    public void create(LocalDate showDate, LocalTime startTime, Room room, Movie movie) {
        iShowtimeRepository.createShowtime(showDate,startTime,room.getId(),movie.getId());
    }
}
