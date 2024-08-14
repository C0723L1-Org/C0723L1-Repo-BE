package org.c07.movie_booking.repository;

import org.c07.movie_booking.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
@Repository
public interface IMovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "SELECT distinct m.*, s.name AS status_name " +
            "FROM movie m " +
            "LEFT JOIN movie_kind_of_film mk ON mk.movie_id = m.id " +
            "LEFT JOIN status_film s ON s.id = m.status_movie_id " +
            "WHERE is_delete = 0 ", nativeQuery = true)
        Page<Movie> findAllByQuery(Pageable pageable);

    @Query("select mv from Movie mv " +
            "left join mv.kindOfFilm k " +
            "left join mv.statusFilmId s " +
            "where mv.isDelete = false " +
            "and (:nameMovie is null or mv.nameMovie like %:nameMovie%) " +
            "and (:director is null or mv.director like %:director%) " +
            "and (:releaseDate is null or DATE(mv.releaseDate) =:releaseDate) " +
            "and (:content is null or mv.content like %:content%) " +
            "and (:actor is null or mv.actor like %:actor%) " +
            "and (:nameStatus is null or s.name like %:nameStatus%) ")
    Page<Movie> getSearchOfFields(
            @Param("nameMovie") String nameMovie,
            @Param("content") String content,
            @Param("director") String director,
            @Param("releaseDate") LocalDate releaseDate,
            @Param("nameStatus") String nameStatus,
            @Param("actor") String actor,
            Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update movie set is_delete = 1 where id =:id", nativeQuery = true)
    void deleteById(@Param("id") Long id);

    @Query(value = "select id from movie where id = :id", nativeQuery = true)
    Long findByIdMovie(@Param("id") Long id);
}
