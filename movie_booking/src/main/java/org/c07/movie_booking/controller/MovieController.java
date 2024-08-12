package org.c07.movie_booking.controller;

import org.c07.movie_booking.dto.KindOfFilmDTO;
import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.model.KindOfFilm;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.service.IKindOfFilmService;
import org.c07.movie_booking.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/movie")
@CrossOrigin
public class MovieController {

    @Autowired
    private IMovieService iMovieService;

    @Autowired
    private IKindOfFilmService iKindOfFilmService;

    //xem chi tiết phim
    @GetMapping("/private/find/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long id) {
        Movie movie = iMovieService.findMovieById(id);
        if (movie != null) {
            MovieDTO movieDTO = iMovieService.convertToDTO(movie);
            return ResponseEntity.ok(movieDTO);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/private/currently-showing")
    public ResponseEntity<?> getCurrentlyShowingMovies() {
        try {
            List<Movie> movies = iMovieService.findCurrentlyShowingMovies();
            List<MovieDTO> movieDTOs = movies.stream()
                    .map(iMovieService::convertToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(movieDTOs);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/private/list-kind-of-film")
    public ResponseEntity<?> getAllKindOfFilm() {
        try {
            List<KindOfFilm> kindOfFilms = iKindOfFilmService.findAllKindOfFilm();
            List<KindOfFilmDTO> kindOfFilmDTOs = kindOfFilms.stream()
                    .map(iKindOfFilmService::convertToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(kindOfFilmDTOs);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
