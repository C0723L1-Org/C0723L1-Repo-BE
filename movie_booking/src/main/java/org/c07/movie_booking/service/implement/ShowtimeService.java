package org.c07.movie_booking.service.implement;

import org.c07.movie_booking.model.Showtime;
import org.c07.movie_booking.repository.IShowtimeRepository;
import org.c07.movie_booking.service.IShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.c07.movie_booking.dto.ShowtimeDTO;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.model.Room;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Service
public class ShowtimeService implements IShowtimeService {
    @Autowired
    IShowtimeRepository showtimeRepository;
    @Override
    public List<Showtime> findShowtimeByIdMovie(Long id,String date,String time) {
        return showtimeRepository.findShowtimeByIdMovie(id,date,time);
    }

    @Override
    public List<ShowtimeDTO> findAllShowtime() {

        return showtimeRepository.findAllShowTime();
    }


    @Override
    public void create(LocalDate showDate, LocalTime startTime, Room room, Movie movie) {
        showtimeRepository.createShowtime(showDate,startTime,room.getId(),movie.getId());
    }
}


