package org.c07.movie_booking.controller;
import org.c07.movie_booking.dto.KindOfFilmDTO;
import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.model.KindOfFilm;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.dto.StatusFilmDTO;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/movie")
@CrossOrigin("*")
public class MovieController {

    @Autowired
    private IMovieService iMovieService;
    @Autowired
    private IStatusFilmService iStatusFilmService;
    @Autowired
    private IKindOfFilmService iKindOfFilmService;

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
    public ResponseEntity<?> getStatusMovie() {
        List<StatusFilm> movies = iStatusFilmService.getStatusMovie();
        if (movies.isEmpty()) {
            return ResponseEntity.status(404).body("Not found");
        }
        return ResponseEntity.ok(movies);
    }
//    @GetMapping("/public/{id}")
//    public ResponseEntity<Movie> getMovieById(@PathVariable Long id){
//        Movie movie =iMovieService.getMovieById(id);
//        System.out.println(movie);
//        return new ResponseEntity<>(movie, HttpStatus.OK);
//    }

    //Manager

    //Tạo mới phim
    @PostMapping("private/create")
    public ResponseEntity<?> createMovie(@RequestBody MovieDTO movieDTO, BindingResult bindingResult) {
        new MovieDTO().validate(movieDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            iMovieService.createMovie(movieDTO);
            return new ResponseEntity<>("Tạo mới thành công", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Tạo mới thất bại" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    //xem chi tiết phim
    @GetMapping("private/find/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long id) {
        Movie movie = iMovieService.findMovieById(id);
        if (movie != null) {
            MovieDTO movieDTO = iMovieService.convertToDTO(movie);
            return ResponseEntity.ok(movieDTO);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Chức năng chỉnh sửa phim
    @PutMapping("private/update/{id}")
    public ResponseEntity<?> updateMovie(@RequestBody MovieDTO movieDTO, @PathVariable long id, BindingResult bindingResult) {
        new MovieDTO().validate(movieDTO, bindingResult);
        try {
            iMovieService.updateMovie(movieDTO, id);
            return new ResponseEntity<>("Update Suceess", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("thất bại" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //lấy danh sách trạng thái phim
    @GetMapping("private/list-status-film")
    public List<StatusFilmDTO> getFindAllStatus() {
            return iStatusFilmService.getFindAll();
    }

    @GetMapping("private/currently-showing")
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
