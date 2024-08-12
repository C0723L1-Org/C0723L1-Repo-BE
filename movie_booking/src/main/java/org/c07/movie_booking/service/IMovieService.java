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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.c07.movie_booking.exception.FieldRequiredException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
public interface IMovieService {
    List<Movie> getSearchField(Map<String, Objects> params);

    void createMovie(MovieDTO movieDTO);
    void updateMovie(MovieDTO movieDTO, Long id);
    Movie findMovieById(long id);
    void deleteById(Long id);
    List<MovieDTO> getSearchFields(String nameMovie, String content, String director,
                                   LocalDate releaseDate,
                                   String nameStatus, String nameKind, String actor);
    MovieDTO convertToDTO(Movie movie);

}
