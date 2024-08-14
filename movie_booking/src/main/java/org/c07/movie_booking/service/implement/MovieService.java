package org.c07.movie_booking.service.implement;

import org.c07.movie_booking.dto.KindOfFilmDTO;
import org.c07.movie_booking.dto.MovieDTO;
import jakarta.transaction.Transactional;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.repository.IMovieRepository;
import org.c07.movie_booking.service.IMovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService implements IMovieService {
    @Autowired
    private IMovieRepository iMovieRepository;
    @Autowired
    private ModelMapper modelMapper;
    //Home
    @Override
    public Page<Movie> getSearchMovie(String nameMovie, String director, LocalDate releaseDate, String nameStatus, String actor, Pageable pageable) {
        return iMovieRepository.getSearchMovie(nameMovie, director, releaseDate, nameStatus, actor, pageable);
    }

    @Override
    public Page<Movie> searchMovieByKindOfFilm(String nameKind, Pageable pageable) {
        return iMovieRepository.searchMovieByKindOfFilm(nameKind,pageable);
    }

    @Override
    public List<Movie> getMovieIsComming() {
        return iMovieRepository.getMovieIsComming();
    }

    @Override
    public List<Movie> getMovieIsShowing() {
        return iMovieRepository.getMovieIsShowing();
    }

    //Manager
    @Override
    public List<Movie> findCurrentlyShowingMovies() {

        return iMovieRepository.findCurrentlyShowingMovies();
    }

    @Override
    public void createMovie(MovieDTO movieDTO) {
        iMovieRepository.createMovie(
                movieDTO.getNameMovie(),
                movieDTO.getReleaseDate(),
                movieDTO.getDurationMovie(),
                movieDTO.getActor(),
                movieDTO.getDirector(),
                movieDTO.getStudio(),
                movieDTO.getContent(),
                movieDTO.getTrailer(),
                movieDTO.getAvatar(),
                movieDTO.getPoster(),
                movieDTO.getDelete(),
                movieDTO.getStatusFilm()
        );
        Long movieId = iMovieRepository.findLastInsertedMovieId();

        if(movieDTO.getKindOfFilm() != null && !movieDTO.getKindOfFilm().isEmpty()){
            for (KindOfFilmDTO kindOfFilm: movieDTO.getKindOfFilm()){
                iMovieRepository.insertKindOfFilmForMovie(movieId,kindOfFilm.getId() );
            }
        }
    }
    @Override
    @Transactional
    public void updateMovie(MovieDTO movieDTO, Long id) {
        iMovieRepository.updateMovie(
                movieDTO.getActor(),
                movieDTO.getAvatar(),
                movieDTO.getContent(),
                movieDTO.getDirector(),
                movieDTO.getDurationMovie(),
                movieDTO.getNameMovie(),
                movieDTO.getReleaseDate(),
                movieDTO.getStudio(),
                movieDTO.getTrailer(),
                movieDTO.getStatusFilm(),
                movieDTO.getPoster(),
                id
        );
        iMovieRepository.deleteAllKindOfFilmByMovie(id);

        for (KindOfFilmDTO kindOfFilm: movieDTO.getKindOfFilm()){
            iMovieRepository.insertKindOfFilmForMovie(id,kindOfFilm.getId() );

        }

    }

    @Override
    public Movie getMovieById(Long id) {

        return null;
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
        movieDTO.setDelete(movie.getDelete());
        movieDTO.setStatusFilm(movie.getStatusFilmId().getId());

        List<KindOfFilmDTO> kindOfFilmDTOs = movie.getKindOfFilms().stream()
                .map(kindOfFilm -> new KindOfFilmDTO(kindOfFilm.getId(), kindOfFilm.getName()))
                .collect(Collectors.toList());
        movieDTO.setKindOfFilm(kindOfFilmDTOs);
        return movieDTO;
    }

    @Override
    public Movie findMovieById(Long id) {
        return iMovieRepository.findMovieById(id).get();
    }

}
