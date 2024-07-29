package org.c07.movie_booking.controller;


import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/movie/public")
@CrossOrigin("*")
public class MovieController {
    @Autowired
    private IMovieService iMovieService;
    @GetMapping("/list-movie")
    public List<MovieDTO> getFindAll(){

        return iMovieService.getFindAll();
    }


//    Show all information of movie and search movie
    @GetMapping("/show-list-movie")
    public ResponseEntity<?> showAndSearchMovie( @RequestParam(value = "searchContent", defaultValue = "") String searchContent,
                                             @RequestParam(value = "page", defaultValue = "0") int page)
    {
        if (page < 0){
            page = 0;
        }
        Page<Movie> movies = iMovieService.searchMovieByNameMovie(searchContent, PageRequest.of(page, 5));
        if(movies.isEmpty())
        {
            return ResponseEntity.status(404).body("Not found");
        }
        return ResponseEntity.ok(movies);
    }
}
