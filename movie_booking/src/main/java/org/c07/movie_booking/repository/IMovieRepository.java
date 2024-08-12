package org.c07.movie_booking.repository;

import org.c07.movie_booking.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Long> {
    @Query(nativeQuery = true,value ="select * from movie where id = ?1")
    Movie getMovieById(Long id);
}
