package org.c07.movie_booking.repository;

import org.c07.movie_booking.model.KindOfFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IKindOfFilmRepository extends JpaRepository<KindOfFilm, Long> {
    @Query(value = "select * from kind_of_film", nativeQuery = true)
    List<KindOfFilm> findAllByQuery();
}
