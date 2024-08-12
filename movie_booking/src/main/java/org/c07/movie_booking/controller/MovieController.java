package org.c07.movie_booking.controller;


import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
@CrossOrigin
public class MovieController {
    @Autowired
    private IMovieService iMovieService;
    @GetMapping("/list-movie")
    public List<MovieDTO> getFindAll(){
        return iMovieService.getFindAll();
    }
    @GetMapping("/public/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id){
        Movie movie =iMovieService.getMovieById(id);
        System.out.println(movie);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }
}
