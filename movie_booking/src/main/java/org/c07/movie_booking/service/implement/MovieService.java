package org.c07.movie_booking.service.implement;

import org.c07.movie_booking.dto.KindOfFilmDTO;
import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.exception.FieldRequiredException;
import org.c07.movie_booking.model.KindOfFilm;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.model.StatusFilm;
import org.c07.movie_booking.repository.IMovieRepository;
import org.c07.movie_booking.service.IMovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService implements IMovieService {
    @Autowired
    private IMovieRepository iMovieRepository;
    @Autowired
    private ModelMapper modelMapper;
    //Home
    @Override
    public Page<Movie> getSearchMovie(String nameMovie, String director, LocalDate releaseDate, String nameStatus, String nameKind, String actor, Pageable pageable) {
        return iMovieRepository.getSearchMovie(nameMovie, director, releaseDate, nameStatus, nameKind, actor, pageable);
    }

    @Override
    public Page<Movie> test(String nameMovie, String director, LocalDate releaseDate, String nameStatus, String nameKind, String actor, Pageable pageable) {
        return iMovieRepository.Test(nameMovie, director, releaseDate, nameStatus,  nameKind, actor, pageable);
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
    public List<MovieDTO> getFindAll() {
        List<Movie> movieEntity = iMovieRepository.findAllByQuery();
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for (Movie item: movieEntity){
            MovieDTO movieDTO = modelMapper.map(item, MovieDTO.class);
            movieDTOList.add(movieDTO);
        }
        return movieDTOList;
    }

    @Override
    public List<MovieDTO> getSearchFields(String nameMovie, String content, String director,
                LocalDate releaseDate,
                String nameStatus, String nameKind, String actor) {
            List<Movie> movies = iMovieRepository.getSearchOfFields(nameMovie, content, director, releaseDate,
                    nameStatus, nameKind, actor);
        // Chuyển đổi sang MovieDTO và trả về
        return movies.stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByIdQuery(Long id) throws FieldRequiredException {
        Long getId = validate(id);
        iMovieRepository.deleteById(getId);
    }
    public Long validate(Long id) throws FieldRequiredException {
        Long getId = null;
        if(id > 0){
            getId = iMovieRepository.findByIdMovie(id);
            if(getId == null){
                throw new FieldRequiredException("id not found or you did not pass the value!");
            }
        }else {
            throw new FieldRequiredException("id not found ex: !id < 0");
        }
        return getId;
    }

    @Override
    public void deleteByIds(List<Long> paths) throws FieldRequiredException {
        for (Long id : paths) {
            deleteByIdQuery(id);
        }
    }


}
