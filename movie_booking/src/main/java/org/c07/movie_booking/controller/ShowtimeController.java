package org.c07.movie_booking.controller;

import org.c07.movie_booking.model.Showtime;
import org.c07.movie_booking.service.IShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/showtime")
@CrossOrigin
public class ShowtimeController {
    @Autowired
    IShowtimeService showtimeService;
    @GetMapping("/public/movie/{id}")
    public ResponseEntity<?> findShowtimeByIdMovie(@PathVariable("id") Long id,@RequestParam String date){
        List<Showtime> showtimeList = showtimeService.findShowtimeByIdMovie(id,"%"+date+"%");
        if(showtimeList.isEmpty()){
            return new ResponseEntity<>(showtimeList, HttpStatus.NO_CONTENT);
        }else return new ResponseEntity<>(showtimeList, HttpStatus.OK);

    }
}
