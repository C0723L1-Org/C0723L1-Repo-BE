package org.c07.movie_booking.service.implement;

import org.c07.movie_booking.model.Booking;
import org.c07.movie_booking.model.User;
import org.c07.movie_booking.repository.IBookingRepository;
import org.c07.movie_booking.repository.IUserRepositoty;
import org.c07.movie_booking.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BookingService implements IBookingService {
    @Autowired
    IBookingRepository bookingRepository;
    @Autowired
    IUserRepositoty userRepositoty;
    @Override
    public boolean addNewBooking(Booking booking) {
        try{
            bookingRepository.save(booking);
            return true;
        }
        catch (Exception e){ return  false;}
    }

    @Override
    public String createCodeBooking() {
        // lay danh sach ra
        List<Booking> bookingList = bookingRepository.findAll();
        if (bookingList.isEmpty()){
            return "1";
        } else {
            int currentCode = Integer.parseInt(bookingRepository.selectCurrentCode());
            return Integer.toString(currentCode +1);
        }
    }

    
}
