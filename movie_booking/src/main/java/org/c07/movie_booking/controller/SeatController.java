package org.c07.movie_booking.controller;

import org.c07.movie_booking.model.Seat;
import org.c07.movie_booking.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seat")
@CrossOrigin
public class SeatController {
    @Autowired
    ISeatService seatService;
    // get all selected seats
    @GetMapping("/public/list")
    public ResponseEntity<List<Seat>> getAllSelectedSeats(){
        List<Seat> seatList = seatService.getAllSelectedSeat();
        if(seatList.isEmpty()){
            return new ResponseEntity<>(seatList, HttpStatus.NO_CONTENT);
        }else return new ResponseEntity<>(seatList, HttpStatus.OK);
    }
}
