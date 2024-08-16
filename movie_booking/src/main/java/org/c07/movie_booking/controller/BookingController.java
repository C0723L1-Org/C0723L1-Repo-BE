package org.c07.movie_booking.controller;

import jakarta.validation.Valid;
import org.c07.movie_booking.dto.BookingDto;
import org.c07.movie_booking.model.Booking;
import org.c07.movie_booking.model.User;
import org.c07.movie_booking.service.EmailService;
import org.c07.movie_booking.service.IBookingService;
import org.c07.movie_booking.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    @Autowired
    IBookingService bookingService;
    @Autowired
    EmailService emailService;
    @Autowired
    IUserService userService;
    @PostMapping("/create/{id}")
    public ResponseEntity<?> createNewBooking(@Valid @RequestBody BookingDto bookingDto,
                                              @PathVariable Long id,
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
        booking.setUser(userService.findUserById(id));
        String msg;
        if(bookingService.addNewBooking(booking)){
            sendEmail(booking.getUser().getEmail(),
                    booking.getCodeBooking(),
                    booking.getShowTime().getMovie().getNameMovie(),
                    booking.getShowTime().getRoom().getRoomName(),
                    booking.getShowTime().getShowDate().toString(),
                    booking.getSeat().getSeatNumber(),
                    Integer.toString(booking.getSeat().getPrice()),
                    Double.toString(booking.getTotalAmount()));
            msg ="Booking success";
            return new ResponseEntity<>(msg,HttpStatus.OK);
        } else {
            msg = "Failed";
            return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
        }
    }
    private void sendEmail(String to, String ticketCode,
                           String movieName, String room,
                           String showtime, String seats, String price, String totalPrice){
        try{
            emailService.sendTicketEmail(
                    to,
                    "Thông tin vé của bạn",
                    ticketCode,
                    movieName,
                    "CGV Vinh Trung Plaza",
                    room,
                    showtime,
                    seats,
                    price,
                    totalPrice);
        } catch (Exception ignored){
            System.out.println(ignored);
        }
    }
}
