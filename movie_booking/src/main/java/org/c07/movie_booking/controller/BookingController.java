package org.c07.movie_booking.controller;

import org.c07.movie_booking.dto.BookingDTO;
import org.c07.movie_booking.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
@CrossOrigin
public class BookingController {

    @Autowired
    private IBookingService iBookingService;

    @GetMapping("/private/booking-custormer")
    public ResponseEntity<List<BookingDTO>> showList() {
        List<BookingDTO> bookings = iBookingService.fillAllBooking();
        return ResponseEntity.ok(bookings);
    }
}
