package org.c07.movie_booking.repository;
import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.model.KindOfFilm;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.model.StatusFilm;
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
//   Home
    @Query(
            nativeQuery = true,
            value = "select m.* from movie m " +
                    "join status_film s on s.id = m.status_movie_id " +
                    "join kind_of_film k on k.id = m.kind_of_movie_id " +
                    "where m.name_movie like ?1 " +
                    "and m.director like ?2 " +
                    "and m.release_date like ?3 " +
                    "and s.name like ?4 " +
                    "and m.actor like ?5",
            countQuery = "select count(m.id) from movie m " +
                    "join status_film s on s.id = m.status_movie_id " +
                    "join kind_of_film k on k.id = m.kind_of_movie_id " +
                    "where m.name_movie like ?1 " +
                    "and m.director like ?2 " +
                    "and m.release_date like ?3 " +
                    "and s.name like ?4 " +
                    "and m.actor like ?5"
    )
    Page<Movie> getSearchMovie(
            String nameMovie,
            String director,
            LocalDate releaseDate,
            String nameStatus,
            String actor,
            Pageable pageable);

    @Query("SELECT m FROM Movie m " +
            "JOIN m.kindOfFilm k " +
            "JOIN m.kindOfFilms ks " +
            "JOIN m.statusFilmId s " +
            "WHERE m.isDelete = FALSE " +
            "AND (:nameKind is null or ks.name LIKE %:nameKind%)")
    Page<Movie> searchMovieByKindOfFilm(
            @Param("nameKind") String nameKind,
            Pageable pageable);


    @Query(nativeQuery = true , value = " SELECT m.* FROM Movie m " +
            " join status_film s on s.id = m.status_movie_id " +
            " WHERE s.id = 1 ")
    List<Movie> getMovieIsComming();

    @Query(nativeQuery = true , value = " SELECT m.* FROM Movie m " +
            " join status_film s on s.id = m.status_movie_id " +
            " WHERE s.id = 2 ")
    List<Movie> getMovieIsShowing();




    //Manager
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

