package org.c07.movie_booking.service.implement;

import org.c07.movie_booking.model.Showtime;
import org.c07.movie_booking.repository.IShowtimeRepository;
import org.c07.movie_booking.service.IShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShowtimeService implements IShowtimeService {
    @Autowired
    IShowtimeRepository showtimeRepository;
    @Override
    public List<Showtime> findShowtimeByIdMovie(Long id,String date) {
        return showtimeRepository.findShowtimeByIdMovie(id,date);
    }
}
