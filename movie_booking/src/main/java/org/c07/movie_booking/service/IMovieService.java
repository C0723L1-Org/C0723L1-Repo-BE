package org.c07.movie_booking.service;

import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.model.Movie;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public interface IMovieService {
    List<MovieDTO> getFindAll();
    List<Movie> getSearchField(Map<String, Objects> params);
    void deleteById(Long id);
    Optional<Movie> getMovieById(Long id);
}
