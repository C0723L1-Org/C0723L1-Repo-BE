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
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Long> {
    /**
     //     * Search for Movie entities by name_movie, actor, kind of film, studio.
     //     * @param searchContent the search content to match against movie_name
     //     * @param pageable the pagination information
     //     * @return a Page of Movie entities matching the search criteria
     //     */

//    1.JPQL
    @Query("SELECT m FROM Movie m " +
            "JOIN m.kindOfFilm k " +
            "JOIN m.statusFilmId s " +
            "WHERE m.isDelete=false AND m.nameMovie LIKE %:searchContent% " +
            "OR m.actor LIKE %:searchContent% " +
            "OR k.name LIKE %:searchContent% " +
            "OR m.studio LIKE %:searchContent%")
    Page<Movie> getSearchMovieByNameMovie(@Param("searchContent") String searchContent, Pageable pageable);

    @Query(value = "select * from movie where is_delete = false", nativeQuery = true)
    List<Movie> findAllByQuery();
    @Query("select mv from Movie mv " +
            "left join mv.kindOfFilm k " +
            "left join mv.statusFilmId s " +
            "where mv.isDelete = false " +
            "and (:nameMovie is null or mv.nameMovie like %:nameMovie%) " +
            "and (:director is null or mv.director like %:director%) " +
            "and (:releaseDate is null or DATE(mv.releaseDate) =:releaseDate) " +
            "and (:content is null or mv.content like %:content%) " +
            "and (:actor is null or mv.actor like %:actor%) " +
            "and (:nameStatus is null or s.name like %:nameStatus%) " +
            "and (:nameKind is null or k.name like %:nameKind%)")
    List<Movie> getSearchOfFields(
            @Param("nameMovie") String nameMovie,
            @Param("content") String content,
            @Param("director") String director,
            @Param("releaseDate") LocalDate releaseDate,
            @Param("nameStatus") String nameStatus,
            @Param("nameKind") String nameKind,
            @Param("actor") String actor
    );
    @Modifying
    @Transactional
    @Query(value = "update movie set is_delete = 0 where id =:id", nativeQuery = true)
    void deleteById(@Param("id") Long id);

    @Query(value = "select id from movie where id = :id", nativeQuery = true)
    Long findByIdMovie(@Param("id") Long id);
}

