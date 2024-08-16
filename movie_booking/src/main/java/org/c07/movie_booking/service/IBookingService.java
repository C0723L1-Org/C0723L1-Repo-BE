package org.c07.movie_booking.service;

import org.c07.movie_booking.model.Booking;
import org.c07.movie_booking.model.User;

public interface IBookingService {
    boolean addNewBooking(Booking booking);
    String createCodeBooking();
}
