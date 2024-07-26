package org.c07.movie_booking.repository;


import org.c07.movie_booking.model.MovieStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieStatusRepository extends JpaRepository<MovieStatus, Long> {
}
