package org.c07.movie_booking.controller;


import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.exception.FieldRequiredException;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class MovieController {
    @Autowired
    private IMovieService iMovieService;
    @GetMapping("v1/list-movie")
    public List<MovieDTO> getFindAll(){
        return iMovieService.getFindAll();
    }
    @GetMapping("v1/searches")
    public List<MovieDTO> getSearchFields(
                            @RequestParam(value = "nameMovie", required = false) String nameMovie,
                            @RequestParam(value = "content", required = false) String content,
                            @RequestParam(value = "director", required = false) String director,
                            @RequestParam(value = "releaseDate", required = false) @DateTimeFormat(pattern = "yyyy/MM/dd") Date releaseDate,
                            @RequestParam(value = "nameStatus", required = false) String nameStatus,
                            @RequestParam(value = "nameKind", required = false) String nameKind,
                            @RequestParam(value = "actor", required = false) String actor
    ){
        List<MovieDTO> dtoList = iMovieService.getSearchFields(nameMovie, content, director,
                releaseDate,
                nameStatus, nameKind, actor);
        return dtoList;

    }
    @GetMapping("v1/searches/a")
    public List<MovieDTO> getSearchByName(
            @RequestParam(value = "nameMovie", required = false) String nameMovie
    ){
        List<MovieDTO> dtoList = iMovieService.getSearchByName(nameMovie);
        return dtoList;
    }

    @PutMapping ("v1/delete/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable(name = "id") Long id){
        try {
            iMovieService.deleteByIdQuery(id);
        } catch (FieldRequiredException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.noContent().build();
    }
    @PutMapping ("v1/delete")
    public ResponseEntity<Void> deleteMovieByIds(@RequestParam List<Long> id){
        iMovieService.deleteByIds(id);
        return ResponseEntity.noContent().build();
    }
}
