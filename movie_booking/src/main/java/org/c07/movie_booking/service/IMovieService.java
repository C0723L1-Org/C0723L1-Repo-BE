package org.c07.movie_booking.service;

import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.model.Movie;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMovieService {
    void createMovie(MovieDTO movieDTO);
    void updateMovie(MovieDTO movieDTO, Long id);
    void deleteById(Long id);
    List<MovieDTO>getSearchFields(String nameMovie, String content, String director,
                                   LocalDate releaseDate,
                                   String nameStatus, String nameKind, String actor);
    MovieDTO convertToDTO(Movie movie);

    // Tìm kiếm phim theo ID
    Movie findMovieById(Long id);

    // Tìm kiếm danh sách phim theo trạng thái
    List<Movie> findCurrentlyShowingMovies();
    // Lấy tất cả phim
    Page<Movie> findAllMovies(Pageable pageable);


}
