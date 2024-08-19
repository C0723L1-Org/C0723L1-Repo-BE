package org.c07.movie_booking.service;

import org.c07.movie_booking.model.Booking;
import org.c07.movie_booking.model.User;
import org.c07.movie_booking.dto.response.BookingDetailResDTO;
import org.c07.movie_booking.dto.response.BookingResDTO;
import org.c07.movie_booking.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface IBookingService {
    boolean addNewBooking(Booking booking);
    String createCodeBooking();
    // Danh sách vé của riêng từng khách hàng
    List<BookingDetailResDTO> getBookingDetails();
    // Danh sách vé chưa in- role nhân viên hoặc admin
    Page<BookingResDTO> SearchBookings(String valueSearch, Pageable pageable);

    // Tìm vé và xem chi tiết vé theo Id
    BookingResDTO findBookingResDTOById(Long id);

    //Nhận vé
    void receiveBookingById( Long id);

    Booking findBookingBySeatIdAndShowtimeId(Long seatId, Long showtimeId);
}








