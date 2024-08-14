package org.c07.movie_booking.repository;

import org.c07.movie_booking.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISeatRepository extends JpaRepository<Seat,Long> {
    @Query(nativeQuery = true,
            value = "select s.* from seat s join booking b on  b.seat_id = s.id where b.booking_status_id = 1")
    List<Seat> getAllSelectedSeat();
    @Query(nativeQuery = true,
            value = "select s.* from seat s join room r on s.room_id = r.id where r.id =?1 and s.seat_number =?2")
    Seat getSeatByRoomIdAndSeatNumber(Long roomId, String seatNumber);
}
