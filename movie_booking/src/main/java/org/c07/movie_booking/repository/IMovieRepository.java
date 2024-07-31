package org.c07.movie_booking.repository;

import jakarta.transaction.Transactional;
import org.c07.movie_booking.model.KindOfFilm;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.model.StatusFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Long> {
   @Modifying
   @Transactional
    @Query(value = "INSERT INTO Movie(actor,avatar,content,director,duration_movie,name_movie,release_date,studio,trailer,kind_of_movie_id,status_movie_id,poster)" + "values (?,?,?,?,?,?,?,?,?,?,?,?)",nativeQuery = true)
    void createMovie(@Param("actor")String actor,@Param("avatar")String avatar, @Param("content")String content, @Param("director") String director, @Param("durationMovie") String durationMovie, @Param("name_movie") String nameMovie, @Param("release_date") LocalDate releaseDate, @Param("studio") String studio,
                     @Param("trailer") String trailer, @Param("typeMovieId") Long kindOfMovieImd, @Param("statusMovieId") Long statusMovieId,@Param("poster") String poster);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Movie m SET m.actor = :actor ,m.avatar = :avatar, m.content = :content, m.director = :director, m.duration_movie = :durationMovie, m.name_movie = :nameMovie, m.release_date = :releaseDate, m.studio = :studio, m.trailer = :trailer, m.kind_of_movie_id = :kindOfFilm, m.status_movie_id = :statusFilmId,m.poster = :poster WHERE m.id = :id", nativeQuery = true)
    void updateMovie(@Param("actor") String actor, @Param("avatar") String avatar, @Param("content") String content, @Param("director") String director, @Param("durationMovie") String durationMovie, @Param("nameMovie") String nameMovie, @Param("releaseDate") LocalDate releaseDate, @Param("studio") String studio, @Param("trailer") String trailer, @Param("kindOfFilm") Long kindOfFilmId, @Param("statusFilmId") Long statusFilmId,@Param("poster") String poster, @Param("id") Long id);


 @Query(value = "select * FROM  Movie  where id=:id", nativeQuery = true)
 Optional<Movie>findMovieById(@Param("id") long id);

}
