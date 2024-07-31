package org.c07.movie_booking.repository;
import org.c07.movie_booking.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}

