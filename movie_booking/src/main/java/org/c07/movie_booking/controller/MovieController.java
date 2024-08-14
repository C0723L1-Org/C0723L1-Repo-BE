package org.c07.movie_booking.controller;


import jakarta.servlet.http.HttpServletResponse;
import org.c07.movie_booking.dto.KindOfFilmDTO;
import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.dto.StatusFilmDTO;
import org.c07.movie_booking.exception.FieldRequiredException;
//import org.c07.movie_booking.service.IFileService;
import org.c07.movie_booking.model.KindOfFilm;
import org.c07.movie_booking.service.IKindOfFilmService;
import org.c07.movie_booking.service.IMovieService;
import org.c07.movie_booking.service.IStatusFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/movie/")
public class MovieController {
    @Autowired
    private IMovieService iMovieService;
    @Autowired
    private IKindOfFilmService iKindOfFilmService;
    @Autowired
    private IStatusFilmService iStatusFilmService;

    @GetMapping("private/list-movie")
    public Page<MovieDTO> getFindAll(
            @RequestParam(value = "pageNumber") int pageNumber,
            @RequestParam(value = "pageSize") int pageSize
    ){
        return iMovieService.getFindAll(pageNumber, pageSize);
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
public Page<MovieDTO> getSearchFields(
        @RequestParam(value = "nameMovie", required = false) String nameMovie,
        @RequestParam(value = "content", required = false) String content,
        @RequestParam(value = "director", required = false) String director,
        @RequestParam(value = "releaseDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate releaseDate,
        @RequestParam(value = "nameStatus", required = false) String nameStatus,
        @RequestParam(value = "actor", required = false) String actor,
        @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

    return iMovieService.getSearchFields(nameMovie, content, director, releaseDate,
            nameStatus, actor, pageNumber, pageSize);
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
