package org.c07.movie_booking.repository;

import org.c07.movie_booking.dto.ShowtimeDTO;
import org.c07.movie_booking.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface IShowtimeRepository extends JpaRepository<Showtime,Long> {
    @Query(nativeQuery = true, value = "select s.* from showtime s where s.movie_id = ?1 and s.show_date like ?2 and s.start_time > ?3")
    List<Showtime> findShowtimeByIdMovie(Long id,String Date,String dateTime);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Showtime(show_date,start_time,movie_id,room_id)" + "values (?,?,?,?)",nativeQuery = true)
    void createShowtime(
            @Param("showDate") LocalDate showDate,
            @Param("startTime") LocalTime startTime,
            @Param("movieId") Long movieId,
            @Param("roomId") Long roomId);


    @Query("select new org.c07.movie_booking.dto.ShowtimeDTO(m.nameMovie,r.roomName,s.showDate,s.startTime) " +
            "from Showtime s " +
            "left join s.room r " +
            "left join s.movie m")
    List<ShowtimeDTO> findAllShowTime();
}

