package org.c07.movie_booking.controller;

import org.c07.movie_booking.model.Seat;
import org.c07.movie_booking.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seat")
@CrossOrigin
public class SeatController {
    @Autowired
    ISeatService seatService;
    // get all selected seats
    @GetMapping("/public/selected")
    public ResponseEntity<List<Seat>> getAllSelectedSeats(@RequestParam Long showtimeId){
        List<Seat> seatList = seatService.getAllSelectedSeat(showtimeId);
        if(seatList.isEmpty()){
            return new ResponseEntity<>(seatList, HttpStatus.NO_CONTENT);
        }else return new ResponseEntity<>(seatList, HttpStatus.OK);
    }
    @GetMapping("/public/selecting")
    public ResponseEntity<List<Seat>> getAllSelectingSeats(@RequestParam Long showtimeId){
        List<Seat> seatList = seatService.getAllSelectingSeat(showtimeId);
        if(seatList.isEmpty()){
            return new ResponseEntity<>(seatList, HttpStatus.NO_CONTENT);
        }else return new ResponseEntity<>(seatList, HttpStatus.OK);
    }
    @GetMapping("/public/seat")
    public ResponseEntity<Seat> getSeatByRoomIdAndSeatNumber(@RequestParam Long roomId, String seatNumber){
        System.out.println(roomId + ";"+seatNumber);
        Seat seat = seatService.getSeatByRoomIdAndSeatNumber(roomId,seatNumber);
        if (seat != null){
            return new ResponseEntity<>(seat, HttpStatus.OK);
        }else return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
