package org.c07.movie_booking.repository;

import org.c07.movie_booking.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IShowtimeRepository extends JpaRepository<Showtime,Long> {
    @Query(nativeQuery = true, value = "select s.* from showtime s where s.movie_id = ?1 and s.show_date like ?2 and s.start_time > ?3")
    List<Showtime> findShowtimeByIdMovie(Long id,String Date,String time);
}
