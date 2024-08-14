package org.c07.movie_booking.service;

import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.exception.FieldRequiredException;
import org.springframework.data.domain.Page;
import java.time.LocalDate;
import java.util.List;

public interface IMovieService {
    Page<MovieDTO> getFindAll(Integer pageNumber, Integer pageSize);
    Page<MovieDTO> getSearchFields(String nameMovie, String content, String director,
                                            LocalDate releaseDate, String nameStatus, String actor,
                                            Integer pageNumber, Integer pageSize);

    void deleteByIdQuery(Long id) throws FieldRequiredException;

    void deleteByIds(List<Long> paths) throws FieldRequiredException;

}
