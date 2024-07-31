package org.c07.movie_booking.service;

import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.model.KindOfFilm;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.model.StatusFilm;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public interface IMovieService {
    List<MovieDTO> getFindAll();
    List<Movie> getSearchField(Map<String, Objects> params);

    void createMovie(String nameMovie, LocalDate releaseDate, String durationMovie, String actor, String director, String studio, String content,
                     String trailer, String avatar, StatusFilm statusFilmId, KindOfFilm kindOfFilm,String poster );
    void updateMovie(String nameMovie, LocalDate releaseDate, String durationMovie, String actor, String director, String studio, String content,
                     String trailer, String avatar, StatusFilm statusFilmId, KindOfFilm kindOfFilm,String poster, long id);
    Optional<Movie> findMovieById(long id);
    void deleteById(Long id);
}
