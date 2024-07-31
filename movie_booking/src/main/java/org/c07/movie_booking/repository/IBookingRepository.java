package org.c07.movie_booking.repository;

import org.c07.movie_booking.dto.BookingDTO;
import org.c07.movie_booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBookingRepository extends JpaRepository<Booking,Long> {
    @Query("SELECT new org.c07.movie_booking.dto.BookingDTO(m.nameMovie, b.dateBooking, b.totalAmount, bs.name) " +
            "FROM Booking b " +
            "JOIN b.showTime s " +
            "JOIN s.movie m " +
            "JOIN b.bookingStatus bs")
    List<BookingDTO> findBookingDetails();
}
