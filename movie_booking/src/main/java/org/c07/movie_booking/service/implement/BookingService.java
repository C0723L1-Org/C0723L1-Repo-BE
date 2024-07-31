package org.c07.movie_booking.service.implement;

import org.c07.movie_booking.dto.BookingDTO;
import org.c07.movie_booking.model.Booking;
import org.c07.movie_booking.repository.IBookingRepository;
import org.c07.movie_booking.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService implements IBookingService {
    @Autowired
    private IBookingRepository iBookingRepository;

    @Override
    public List<BookingDTO> fillAllBooking() {
        return iBookingRepository.findBookingDetails();
    }
}
