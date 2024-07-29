package org.c07.movie_booking.service.implement;

import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.exception.FieldRequiredException;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.repository.IMovieRepository;
import org.c07.movie_booking.service.IMovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<MovieDTO> getSearchByName(String nameMovie) {
        List<Movie> movies = iMovieRepository.getSearchByName(nameMovie);
        // Chuyển đổi sang MovieDTO và trả về
        return movies.stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByIdQuery(Long id) throws FieldRequiredException {
       validate(id);
       iMovieRepository.deleteById(id);
    }
    public void validate(Long id) throws FieldRequiredException {
        if(id > 0){
            Long getId = iMovieRepository.findByIdMovie(id);
            if(getId == null){
                throw new FieldRequiredException("id not found or you did not pass the value!");
            }
            throw new FieldRequiredException("id cannot be less than 0");
        }
    }

    @Override
    public void deleteByIds(List<Long> paths) {
        for (Long item: paths){
            iMovieRepository.deleteById(item);
        }
    }
}
