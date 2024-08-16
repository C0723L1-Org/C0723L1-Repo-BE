package org.c07.movie_booking.repository;
import org.c07.movie_booking.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

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

    @Query(nativeQuery = true,value ="select * from movie where id = ?1")
    Movie getMovieById(Long id);

    //Manager

    //Query tìm kiếm phim đang chiếu
    @Query("SELECT m FROM Movie m LEFT JOIN FETCH m.kindOfFilms k WHERE m.statusFilmId.id = 1")
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
//
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
            "and (:releaseDateFrom is null or :releaseDateTo is null or DATE(mv.releaseDate) between :releaseDateFrom and :releaseDateTo) " +
            "and (:content is null or mv.content like %:content%) " +
            "and (:actor is null or mv.actor like %:actor%) " +
            "and (:nameStatus is null or s.name like %:nameStatus%) ")
    Page<Movie> getSearchOfFields(
            @Param("nameMovie") String nameMovie,
            @Param("content") String content,
            @Param("director") String director,
            @Param("releaseDateFrom") LocalDate releaseDateFrom,
            @Param("releaseDateTo") LocalDate releaseDateTo,
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

