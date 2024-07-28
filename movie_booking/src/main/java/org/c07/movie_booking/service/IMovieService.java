package org.c07.movie_booking.service;

import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface IMovieService {
    List<MovieDTO> getFindAll();
    List<Movie> getSearchField(Map<String, Objects> params);
    void deleteById(Long id);
    Page<Movie> searchMovieByNameMovie(String searchContent, Pageable pageable);

}