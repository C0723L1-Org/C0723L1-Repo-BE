package org.c07.movie_booking.repository;

import jakarta.transaction.Transactional;
import org.c07.movie_booking.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Long> {

    //Tìm kiếm phim theo id
    @Query(value = "SELECT * FROM movie WHERE id = :id", nativeQuery = true)
    Optional<Movie> findMovieById(@Param("id") Long id);

    //Query tìm kiếm phim đang chiếu
    @Query("SELECT m FROM Movie m LEFT JOIN FETCH m.kindOfFilms k WHERE m.statusFilm.id = 1")
    List<Movie> findCurrentlyShowingMovies();

}
