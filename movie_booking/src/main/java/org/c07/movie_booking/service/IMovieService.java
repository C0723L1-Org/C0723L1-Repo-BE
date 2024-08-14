package org.c07.movie_booking.service;

import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.model.Movie;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.c07.movie_booking.exception.FieldRequiredException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface IMovieService {
    //home
    Page<Movie> getSearchMovie(String nameMovie, String director, LocalDate releaseDate,
                               String nameStatus, String actor, Pageable pageable);

    Page<Movie> searchMovieByKindOfFilm(String nameKind, Pageable pageable);

    List<Movie> getMovieIsComming();

    List<Movie> getMovieIsShowing();
    Movie getMovieById(Long id);

    //Manager
    void createMovie(MovieDTO movieDTO);
    void updateMovie(MovieDTO movieDTO, Long id);

    // Tìm kiếm phim theo ID
    Movie findMovieById(Long id);

    // Tìm kiếm danh sách phim theo trạng thái
    List<Movie> findCurrentlyShowingMovies();

    // Lấy tất cả phim
    MovieDTO convertToDTO(Movie movie);
}