package org.c07.movie_booking.repository;

import org.c07.movie_booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Long> {
    @Query(nativeQuery = true, value = "select MAX(code_booking) from booking")
    String selectCurrentCode();
}
