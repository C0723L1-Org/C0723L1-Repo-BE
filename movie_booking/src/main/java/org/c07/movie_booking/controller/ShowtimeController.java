package org.c07.movie_booking.controller;

import org.c07.movie_booking.model.Showtime;
import org.c07.movie_booking.service.IShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.c07.movie_booking.dto.ShowtimeDTO;
import org.springframework.validation.BindingResult;
@RestController
@RequestMapping("/api/v1/showtime")
@CrossOrigin
public class ShowtimeController {
    @Autowired
    IShowtimeService showtimeService;
    @GetMapping("/public/list")
    public ResponseEntity<?> findShowtimeByIdMovie(@RequestParam Long movieId,@RequestParam String date, @RequestParam String time){
        List<Showtime> showtimeList = showtimeService.findShowtimeByIdMovie(movieId,"%"+date+"%",time);
        if(showtimeList.isEmpty()){
            return new ResponseEntity<>(showtimeList, HttpStatus.NO_CONTENT);
        }else return new ResponseEntity<>(showtimeList, HttpStatus.OK);

    }
    @GetMapping("private/list-showtime")
    public List<ShowtimeDTO> getFindAll(){
        return showtimeService.findAllShowtime();
    }
    @PostMapping("private/create")
    public ResponseEntity<ShowtimeDTO> createShowTime(@RequestBody ShowtimeDTO showtimeDTO, BindingResult bindingResult){
        new ShowtimeDTO().validate(showtimeDTO,bindingResult);
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
        try {
            showtimeService.create(showtimeDTO.getShowDate(),showtimeDTO.getStartTime(),showtimeDTO.getRoom(),showtimeDTO.getMoviel());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

