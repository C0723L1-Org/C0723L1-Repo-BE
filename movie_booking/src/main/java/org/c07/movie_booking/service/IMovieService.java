package org.c07.movie_booking.service;

import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.exception.FieldRequiredException;
import org.c07.movie_booking.model.Movie;
import org.springframework.data.domain.Page;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface IMovieService {
    //home
    Page<Movie> getSearchMovie(String nameMovie, String director, LocalDate releaseDate,
                               String nameStatus, String actor, Pageable pageable);
    Page<MovieDTO> getFindAll(Integer pageNumber, Integer pageSize);
    Page<Movie> searchMovieByKindOfFilm(String nameKind, Pageable pageable);

    List<Movie> getMovieIsComming();

    List<Movie> getMovieIsShowing();
    Movie getMovieById(Long id);

    //Manager
    Page<MovieDTO> getSearchFields(String nameMovie, String content, String director,
                                   LocalDate releaseDateFrom, LocalDate releaseDateTo, String nameStatus, String actor,
                                   Integer pageNumber, Integer pageSize);


    void deleteByIdQuery(Long id) throws FieldRequiredException;
    void deleteByIds(List<Long> paths);

    void createMovie(MovieDTO movieDTO);
    void updateMovie(MovieDTO movieDTO, Long id);

    // Tìm kiếm phim theo ID
    Movie findMovieById(Long id);

    // Tìm kiếm danh sách phim theo trạng thái
    List<Movie> findCurrentlyShowingMovies();

    // Lấy tất cả phim
    MovieDTO convertToDTO(Movie movie);
}