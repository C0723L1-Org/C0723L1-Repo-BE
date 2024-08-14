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
@RequestMapping("/api/v1/showtime")
@CrossOrigin
public class ShowtimeController {
    @Autowired
    IShowtimeService iShowtimeService;
    @GetMapping("/public/list")
    public ResponseEntity<?> findShowtimeByIdMovie(@RequestParam Long movieId,@RequestParam String date, @RequestParam String time){
        String dateTime = date + " "+time;
        System.out.println("dateTime: " +dateTime);
        System.out.println("date: " +date);
        List<Showtime> showtimeList = iShowtimeService.findShowtimeByIdMovie(movieId,"%"+date+"%",dateTime);
        if(showtimeList.isEmpty()){
            return new ResponseEntity<>(showtimeList, HttpStatus.NO_CONTENT);
        }else return new ResponseEntity<>(showtimeList, HttpStatus.OK);

    }
    @GetMapping("private/list-showtime")
    public List<ShowtimeDTO> getFindAll(){
        return iShowtimeService.findAllShowtime();
    }
    @PostMapping("private/create")
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

