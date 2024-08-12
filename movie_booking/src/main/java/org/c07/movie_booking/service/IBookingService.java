package org.c07.movie_booking.service;

import org.c07.movie_booking.model.Booking;

public interface IBookingService {
    boolean addNewBooking(Booking booking);
    String createCodeBooking();
}
