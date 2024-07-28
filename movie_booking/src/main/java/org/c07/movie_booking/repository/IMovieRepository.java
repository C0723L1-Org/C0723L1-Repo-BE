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
     //     * Search for Movie entities by name_movie.
     //     * @param searchContent the search content to match against movie_name
     //     * @param pageable the pagination information
     //     * @return a Page of Movie entities matching the search criteria
     //     */

//      1.SQL
    
//    @Query( value = "SELECT m.id, m.actor, m.avatar, m.content, m.director, m.duration_movie, " +
//            " m.name_movie, m.release_date, m.studio, m.trailer FROM movie m " +
//            " JOIN kind_of_film k on m.type_movie_id = k.id " +
//            " JOIN status_film s on m.status_movie_id = s.id " +
//            " WHERE m.name_movie LIKE %:searchContent%", nativeQuery = true)
//    Page<Movie> searchMovieByNameMovie(@Param("searchContent") String searchContent, Pageable pageable);
//
//    2.JPQL
    @Query("SELECT m FROM Movie m " +
            "JOIN m.kindOfFilm k " +
            "JOIN m.statusFilmId s " +
            "WHERE m.nameMovie LIKE %:searchContent%")
    Page<Movie> searchMovieByNameMovie(@Param("searchContent") String searchContent, Pageable pageable);
}

