package org.c07.movie_booking.controller;

import org.c07.movie_booking.dto.ShowtimeDTO;
import org.c07.movie_booking.model.Showtime;
import org.c07.movie_booking.service.IShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/showtime/private")
public class ShowtimeController {
    @Autowired
    private IShowtimeService iShowtimeService;
    @GetMapping("/list-showtime")
    public List<ShowtimeDTO> getFindAll(){
        return iShowtimeService.findAllShowtime();
    }
    @PostMapping("/create")
    public ResponseEntity<ShowtimeDTO> createShowTime(@RequestBody ShowtimeDTO showtimeDTO, BindingResult bindingResult){
        new ShowtimeDTO().validate(showtimeDTO,bindingResult);
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
        try {
            iShowtimeService.create(showtimeDTO.getShowDate(),showtimeDTO.getStartTime(),showtimeDTO.getRoom(),showtimeDTO.getMoviel());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        }

    }
}
