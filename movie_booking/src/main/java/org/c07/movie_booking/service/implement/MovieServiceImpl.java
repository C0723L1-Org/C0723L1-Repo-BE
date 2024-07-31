package org.c07.movie_booking.service.implement;

import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.model.KindOfFilm;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.model.StatusFilm;
import org.c07.movie_booking.repository.IMovieRepository;
import org.c07.movie_booking.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class MovieServiceImpl implements IMovieService {
    @Autowired
    private IMovieRepository movieRepository;


    @Override
    public List<MovieDTO> getFindAll() {
        List<Movie> movieEntity = movieRepository.findAll();
        List<MovieDTO> movieDTOList = new ArrayList<>();
        return movieDTOList;
    }

    @Override
    public List<Movie> getSearchField(Map<String, Objects> params) {
        return null;
    }

    @Override
    public void createMovie(String nameMovie, LocalDate releaseDate, String durationMovie, String actor, String director, String studio, String content, String trailer, String avatar, StatusFilm statusFilmId, KindOfFilm kindOfFilm,String poster ) {
        movieRepository.createMovie(actor,avatar,content,director,durationMovie,nameMovie,releaseDate,studio,trailer,kindOfFilm.getId(),statusFilmId.getId(),poster);
    }


    @Override
    public void updateMovie(String nameMovie, LocalDate releaseDate, String durationMovie, String actor, String director, String studio, String content, String trailer, String avatar, StatusFilm statusFilmId, KindOfFilm kindOfFilm,String poster, long id) {
        movieRepository.updateMovie(actor,avatar,content,director,durationMovie,nameMovie,releaseDate,studio,trailer,kindOfFilm.getId(),statusFilmId.getId(),poster,id);
    }

    @Override
    public Optional<Movie> findMovieById(long id) {
        return movieRepository.findMovieById(id);
    }

    @Override
    public void deleteById(Long id) {

    }
}
