package org.c07.movie_booking.repository;

import jakarta.transaction.Transactional;
import org.c07.movie_booking.model.Movie;
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
    //Query tìm kiếm phim đang chiếu
    @Query("SELECT m FROM Movie m LEFT JOIN FETCH m.kindOfFilms k WHERE m.statusFilm.id = 1")
    List<Movie> findCurrentlyShowingMovies();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO movie (name_movie, release_date, duration_movie, actor, director, studio, content, trailer, avatar, poster, is_delete, status_movie_id) VALUES (:nameMovie, :releaseDate, :durationMovie, :actor, :director, :studio, :content, :trailer, :avatar, :poster, :isDelete, :statusFilmId)", nativeQuery = true)
    void createMovie(
            @Param("nameMovie") String nameMovie,
            @Param("releaseDate") LocalDate releaseDate,
            @Param("durationMovie") String durationMovie,
            @Param("actor") String actor,
            @Param("director") String director,
            @Param("studio") String studio,
            @Param("content") String content,
            @Param("trailer") String trailer,
            @Param("avatar") String avatar,
            @Param("poster") String poster,
            @Param("isDelete") boolean isDelete,
            @Param("statusFilmId") Long statusFilmId
    );

    //Query lấy bản ghi vừa thêm
    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    Long findLastInsertedMovieId();

    //Query chỉnh sửa phim
    @Modifying
    @Transactional
    @Query(value = "UPDATE Movie m SET m.actor = :actor ,m.avatar = :avatar, m.content = :content, m.director = :director, m.duration_movie = :durationMovie, m.name_movie = :nameMovie, m.release_date = :releaseDate, m.studio = :studio, m.trailer = :trailer, m.status_movie_id = :statusFilmId,m.poster = :poster WHERE m.id = :id", nativeQuery = true)
    void updateMovie(
            @Param("actor") String actor,
            @Param("avatar") String avatar,
            @Param("content") String content,
            @Param("director") String director,
            @Param("durationMovie") String durationMovie,
            @Param("nameMovie") String nameMovie,
            @Param("releaseDate") LocalDate releaseDate,
            @Param("studio") String studio,
            @Param("trailer") String trailer,
            @Param("statusFilmId") Long statusFilmId,
            @Param("poster") String poster,
            @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM movie_kind_of_film where movie_id= :movieId",nativeQuery = true)
    void deleteAllKindOfFilmByMovie(@Param("movieId") Long movieId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO movie_kind_of_film (movie_id,kind_of_film_id) VALUES (:movieId, :kindOfFilmId)",nativeQuery = true)
    void insertKindOfFilmForMovie(@Param("movieId") Long movieId, @Param("kindOfFilmId") Long kindOfFilmId);

    //Tìm kiếm phim theo id
    @Query(value = "select * FROM  Movie  where id=:id", nativeQuery = true)
    Optional<Movie>findMovieById(@Param("id") long id);

}

