package org.c07.movie_booking.service;

import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.exception.FieldRequiredException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IMovieService {
    List<MovieDTO> getFindAll();
    List<MovieDTO> getSearchFields(String nameMovie, String content, String director,
                                   LocalDate releaseDate,
                                   String nameStatus, String nameKind, String actor);

    List<MovieDTO> getSearchByName(String nameMovie);

    void deleteByIdQuery(Long id) throws FieldRequiredException;

    void deleteByIds(List<Long> paths);

}
