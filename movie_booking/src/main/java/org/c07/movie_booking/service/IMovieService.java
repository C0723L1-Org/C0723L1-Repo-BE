package org.c07.movie_booking.service;

import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.model.KindOfFilm;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.model.StatusFilm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.c07.movie_booking.exception.FieldRequiredException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IMovieService {
    Page<Movie> getSearchMovie(String nameMovie, String director, LocalDate releaseDate,
                               String nameStatus, String actor, Pageable pageable);
    Page<Movie> searchMovieByKindOfFilm(String nameKind, Pageable pageable);
    List<Movie> getMovieIsComming();
    List<Movie> getMovieIsShowing();
    List<MovieDTO> getFindAll();
    List<MovieDTO> getSearchFields(String nameMovie, String content, String director,
                                   LocalDate releaseDate,
                                   String nameStatus, String nameKind, String actor);

    void deleteByIdQuery(Long id) throws FieldRequiredException;

    void deleteByIds(List<Long> paths) throws FieldRequiredException;

}
