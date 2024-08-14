package org.c07.movie_booking.repository;
import org.c07.movie_booking.model.StatusFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IStatusFilmRepository extends JpaRepository<StatusFilm, Long> {
    @Query(value = "select * from status_film", nativeQuery = true)
    List<StatusFilm> findAllByQuery();
    @Query("SELECT s FROM StatusFilm s")
    List<StatusFilm> getMovieStatusFilm();
}
