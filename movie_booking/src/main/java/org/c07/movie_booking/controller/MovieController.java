package org.c07.movie_booking.controller;


import org.c07.movie_booking.dto.KindOfFilmDTO;
import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.dto.StatusFilmDTO;
import org.c07.movie_booking.exception.FieldRequiredException;
import org.c07.movie_booking.service.IKindOfFilmService;
import org.c07.movie_booking.service.IMovieService;
import org.c07.movie_booking.service.IStatusFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/movie/private")
public class MovieController {
    @Autowired
    private IMovieService iMovieService;
    @Autowired
    private IKindOfFilmService iKindOfFilmService;
    @Autowired
    private IStatusFilmService iStatusFilmService;
    @GetMapping("/list-movie")
    public List<MovieDTO> getFindAll(){
        return iMovieService.getFindAll();
    }
    @GetMapping("/list-kind-of-film")
    public List<KindOfFilmDTO> getFindAllKindOfFilm(){
        return iKindOfFilmService.getFindAll();
    }
    @GetMapping("/list-status-film")
    public List<StatusFilmDTO> getFindAllStatus(){
        return iStatusFilmService.getFindAll();
    }
//    @GetMapping("/searches")
    public List<MovieDTO> getSearchFields(
                            @RequestParam(value = "nameMovie", required = false) String nameMovie,
                            @RequestParam(value = "content", required = false) String content,
                            @RequestParam(value = "director", required = false) String director,
                            @RequestParam(value = "releaseDate", required = false) @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate releaseDate,
                            @RequestParam(value = "nameStatus", required = false) String nameStatus,
                            @RequestParam(value = "nameKind", required = false) String nameKind,
                            @RequestParam(value = "actor", required = false) String actor
    ){
        List<MovieDTO> dtoList = iMovieService.getSearchFields(nameMovie, content, director, releaseDate,
                nameStatus, nameKind, actor);
        return dtoList;

    }
    @GetMapping("/nameSearches")
    public List<MovieDTO> getSearchByName(
            @RequestParam(value = "nameMovie", required = false) String nameMovie
    ){
        List<MovieDTO> dtoList = iMovieService.getSearchByName(nameMovie);
        return dtoList;
    }

    @PutMapping ("delete/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable(name = "id") Long id) throws FieldRequiredException {
            iMovieService.deleteByIdQuery(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping ("v1/delete")
    public ResponseEntity<Void> deleteMovieByIds(@RequestParam List<Long> id){
        iMovieService.deleteByIds(id);
        return ResponseEntity.noContent().build();
    }
}
