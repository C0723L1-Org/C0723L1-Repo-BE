package org.c07.movie_booking.controller;

import jakarta.validation.Valid;
import org.c07.movie_booking.dto.BookingDto;
import org.c07.movie_booking.model.Booking;
import org.c07.movie_booking.service.IBookingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    @Autowired
    IBookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<?> createNewBooking(@Valid @RequestBody BookingDto bookingDto,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> map = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                map.put(error.getField(), error.getDefaultMessage());
            });
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        bookingDto.setCodeBooking(bookingService.createCodeBooking());
        Booking booking = new Booking();
        BeanUtils.copyProperties(bookingDto,booking);
        String msg;
        if(bookingService.addNewBooking(booking)){
            msg ="Booking success";
            return new ResponseEntity<>(msg,HttpStatus.OK);
        } else {
            msg = "Failed";
            return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
        }
    }
}
