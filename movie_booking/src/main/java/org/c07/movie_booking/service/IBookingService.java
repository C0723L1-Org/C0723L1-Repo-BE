package org.c07.movie_booking.service;

import org.c07.movie_booking.dto.BookingDTO;

import java.util.List;

public interface IBookingService {
    List<BookingDTO> fillAllBooking();
}
