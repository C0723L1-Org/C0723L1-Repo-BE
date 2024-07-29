package org.c07.movie_booking.service.implement;

import org.c07.movie_booking.model.Booking;
import org.c07.movie_booking.repository.IBookingRepository;
import org.c07.movie_booking.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService implements IBookingService {
    @Autowired
    IBookingRepository bookingRepository;
    @Override
    public boolean addNewBooking(Booking booking) {
        try{
            bookingRepository.save(booking);
            return true;
        }
        catch (Exception e){ return  false;}
    }
}
