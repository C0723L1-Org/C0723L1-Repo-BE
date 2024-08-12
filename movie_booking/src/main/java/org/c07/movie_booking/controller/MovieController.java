package org.c07.movie_booking.controller;

import org.c07.movie_booking.dto.KindOfFilmDTO;
import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.model.KindOfFilm;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.dto.StatusFilmDTO;
import org.c07.movie_booking.exception.FieldRequiredException;
import org.c07.movie_booking.model.StatusFilm;
import org.c07.movie_booking.service.IKindOfFilmService;
import org.c07.movie_booking.service.IMovieService;
import org.c07.movie_booking.service.IStatusFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("api/v1/movie/")
@CrossOrigin("*")
public class MovieController {
    @Autowired
    private IMovieService iMovieService;
    @Autowired
    private IKindOfFilmService iKindOfFilmService;
    @Autowired
    private IStatusFilmService iStatusFilmService;
    //Home
    @GetMapping("public/show-search-movie")
    public ResponseEntity<?> showAndSearchMovie(
            @RequestParam(defaultValue = "") String nameMovie,
            @RequestParam(defaultValue = "") String director,
            @RequestParam(defaultValue = "2024-02-25") @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate releaseDate,
            @RequestParam(defaultValue = "") String nameStatus,
            @RequestParam(defaultValue = "") String actor,
            @RequestParam(defaultValue = "0") int page) {
        if (page < 0) {
            page = 0;
        }
        Page<Movie> movies = iMovieService.getSearchMovie("%"+nameMovie+"%", "%"+director+"%", releaseDate, "%"+nameStatus+"%", "%"+actor+"%", PageRequest.of(page, 5));
        return ResponseEntity.ok(movies);
    }
    @GetMapping("public/search-movie-by-kind")
    public ResponseEntity<?> Test(
            @RequestParam(value = "nameKind", defaultValue = "") String nameKind,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        if (page < 0) {
            page = 0;
        }
        Page<Movie> movies = iMovieService.searchMovieByKindOfFilm(nameKind, PageRequest.of(page, 5));
        return ResponseEntity.ok(movies);
    }



    @GetMapping("public/show-list-movie-showing")
    public ResponseEntity<?> getMovieIsShowing(){
        List<Movie> movies = iMovieService.getMovieIsShowing();
        if (movies.isEmpty()) {
            return ResponseEntity.status(404).body("Not found");
        }
        return ResponseEntity.ok(movies);
    }
    @GetMapping("public/show-list-movie-comming")
    public ResponseEntity<?> getMovieIsComming(){
        List<Movie> movies = iMovieService.getMovieIsComming();
        if (movies.isEmpty()) {
            return ResponseEntity.status(404).body("Not found");
        }
        return ResponseEntity.ok(movies);
    }
    @GetMapping("public/show-list-kindofmovie")
    public ResponseEntity<?> getKindOfMovie(){
        List<KindOfFilm> movies = iKindOfFilmService.getKindOfMovie();
        if (movies.isEmpty()) {
            return ResponseEntity.status(404).body("Not found");
        }
        return ResponseEntity.ok(movies);
    }
    @GetMapping("public/show-list-statusmovie")
    public ResponseEntity<?> getStatusMovie(){
        List<StatusFilm> movies = iStatusFilmService.getStatusMovie();
        if (movies.isEmpty()) {
            return ResponseEntity.status(404).body("Not found");
        }
        return ResponseEntity.ok(movies);
    }

//    Manager
    @GetMapping("private/list-movie")
    public List<MovieDTO> getFindAll(){
        return iMovieService.getFindAll();
    }
    @GetMapping("private/list-kind-of-film")
    public List<KindOfFilmDTO> getFindAllKindOfFilm(){

        return iKindOfFilmService.getFindAll();
    }
    @GetMapping("private/list-status-film")
    public List<StatusFilmDTO> getFindAllStatus(){

        return iStatusFilmService.getFindAll();
    }
    @GetMapping("private/searches")
    public List<MovieDTO> getSearchFields(
            @RequestParam(value = "nameMovie", defaultValue = "" ,required = false) String nameMovie,
            @RequestParam(value = "content", defaultValue = "", required = false) String content,
            @RequestParam(value = "director", defaultValue = "", required = false) String director,
            @RequestParam(value = "releaseDate", defaultValue = "", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate releaseDate,
            @RequestParam(value = "nameStatus", defaultValue = "", required = false) String nameStatus,
            @RequestParam(value = "nameKind", defaultValue = "", required = false) String nameKind,
            @RequestParam(value = "actor", defaultValue = "", required = false) String actor,
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "2") int pageSize
    ){
        List<MovieDTO> dtoList = iMovieService.getSearchFields(nameMovie, content, director, releaseDate,
                nameStatus, nameKind, actor);
        return dtoList;

    }

    @PutMapping ("private/delete/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable(name = "id") Long id) throws FieldRequiredException {
            iMovieService.deleteByIdQuery(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping ("private/list-delete")
    public ResponseEntity<Void> deleteMovieByIds(@RequestParam List<Long> id) throws FieldRequiredException {
        iMovieService.deleteByIds(id);
        return ResponseEntity.noContent().build();
    }
}
