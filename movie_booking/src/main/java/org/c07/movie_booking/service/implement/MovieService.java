package org.c07.movie_booking.service.implement;


import org.c07.movie_booking.dto.KindOfFilmDTO;
import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.repository.IMovieRepository;
import org.c07.movie_booking.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService implements IMovieService {

    @Autowired
    private IMovieRepository iMovieRepository;

    @Override
    public Movie findMovieById(Long id) {
        return iMovieRepository.findMovieById(id).get();
    }

    @Override
    public List<Movie> findCurrentlyShowingMovies() {
        return iMovieRepository.findCurrentlyShowingMovies();
    }

    @Override
    public MovieDTO convertToDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setNameMovie(movie.getNameMovie());
        movieDTO.setReleaseDate(movie.getReleaseDate());
        movieDTO.setDurationMovie(movie.getDurationMovie());
        movieDTO.setActor(movie.getActor());
        movieDTO.setDirector(movie.getDirector());
        movieDTO.setStudio(movie.getStudio());
        movieDTO.setContent(movie.getContent());
        movieDTO.setTrailer(movie.getTrailer());
        movieDTO.setAvatar(movie.getAvatar());
        movieDTO.setPoster(movie.getPoster());
        movieDTO.setDelete(movie.isDelete());
        movieDTO.setStatusFilm(movie.getStatusFilm().getId());

        List<KindOfFilmDTO> kindOfFilmDTOs = movie.getKindOfFilms().stream()
                .map(kindOfFilm -> new KindOfFilmDTO(kindOfFilm.getId(), kindOfFilm.getName()))
                .collect(Collectors.toList());
        movieDTO.setKindOfFilm(kindOfFilmDTOs);
        return movieDTO;
    }
}
