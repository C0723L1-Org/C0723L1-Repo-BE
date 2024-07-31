package org.c07.movie_booking.repository;

import org.c07.movie_booking.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IBookingRepository extends JpaRepository<Booking,Long> {
//    @Query(value = "SELECT b.* " +
//            "FROM booking b " +
//            "JOIN booking_status bs r ON b.booking_status_id = bs.id " +
//            "JOIN showtime st ON b.booking_status_id = st.id " +
//            "JOIN user u ON b.user_id = u.id " +
//            "JOIN seat s ON b.seat_id = s.id " +
//            "WHERE r.name = 'employee' AND u.status = false " +
//            "AND (u.name LIKE %:valueSearch% OR u.code LIKE %:valueSearch%)",
//            countQuery = "SELECT COUNT(*) " +
//                    "FROM user u " +
//                    "JOIN role r ON u.role_id = r.id " +
//                    "WHERE r.name = 'employee' AND u.status = false " +
//                    "AND (u.name LIKE %:valueSearch% OR u.code LIKE %:valueSearch%)", nativeQuery = true)
    @Query(value = "SELECT b.* " +
            "FROM booking b " +
            "JOIN booking_status bs ON b.booking_status_id = bs.id " +
            "JOIN showtime st ON b.showtime_id = st.id " +
            "JOIN user u ON b.user_id = u.id " +
            "JOIN seat s ON b.seat_id = s.id " +
            "WHERE bs.name = false "
            , nativeQuery = true)
    Page<Booking> SearchEmployees(@Param("valueSearch") String valueSearch, Pageable pageable);
}
