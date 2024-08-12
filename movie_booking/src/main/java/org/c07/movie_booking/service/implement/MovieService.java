package org.c07.movie_booking.service.implement;

import jakarta.transaction.Transactional;
import org.c07.movie_booking.dto.KindOfFilmDTO;
import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.repository.IMovieRepository;
import org.c07.movie_booking.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class MovieService implements IMovieService {
    @Autowired
    private IMovieRepository iMovieRepository;

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
    public void deleteById(Long id) {

    }

    @Override
    public List<MovieDTO> getSearchFields(String nameMovie, String content, String director, LocalDate releaseDate, String nameStatus, String nameKind, String actor) {
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
        movieDTO.setStatusFilm(movie.getStatusFilm().getId());

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
