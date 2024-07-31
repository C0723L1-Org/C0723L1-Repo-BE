package org.c07.movie_booking.controller;


import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movie/private")
public class MovieController {
    @Autowired
    private IMovieService iMovieService;
    @GetMapping("/list-movie")
    public List<MovieDTO> getFindAll(){
        return iMovieService.getFindAll();
    }
    @PostMapping("/create")
    public ResponseEntity<MovieDTO> createMovie(@RequestBody MovieDTO movieDTO, BindingResult bindingResult){
        new MovieDTO().validate(movieDTO,bindingResult);
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            iMovieService.createMovie(movieDTO.getNameMovie(),movieDTO.getReleaseDate(),movieDTO.getDurationMovie(),movieDTO.getActor(),movieDTO.getDirector(),movieDTO.getStudio(),movieDTO.getContent(),movieDTO.getTrailer(),movieDTO.getAvatar(),movieDTO.getStatusFilm(),movieDTO.getKindOfFilm(),movieDTO.getPoster());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") long id) {
        Optional<Movie> movie = iMovieService.findMovieById(id);
        return movie.map(m -> new ResponseEntity<>(m, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMovie(@RequestBody MovieDTO movieDTO,@PathVariable long id,BindingResult bindingResult){
        new MovieDTO().validate(movieDTO,bindingResult);
        try {
            iMovieService.updateMovie(movieDTO.getNameMovie(),movieDTO.getReleaseDate(),movieDTO.getDurationMovie(),movieDTO.getActor(),movieDTO.getDirector(),movieDTO.getStudio(),movieDTO.getContent(),movieDTO.getTrailer(),movieDTO.getAvatar(),movieDTO.getStatusFilm(),movieDTO.getKindOfFilm(),movieDTO.getPoster(),id);
            return new ResponseEntity<>("Update Suceess",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        }



}
