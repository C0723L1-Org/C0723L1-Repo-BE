package org.c07.movie_booking.service.implement;

import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.exception.FieldRequiredException;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.repository.IMovieRepository;
import org.c07.movie_booking.service.IMovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements IMovieService {
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
                                          Date releaseDate,
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
    public void deleteByIdQuery(Long id) {
//        Movie movie = iMovieRepository.findById(id).get();
//        if(movie.getId() != id){
//            throw new FieldRequiredException("id not found or you did not pass the value!");
//        }
        iMovieRepository.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Long> paths) {
        for (Long item: paths){
            iMovieRepository.deleteById(item);
        }
    }

}
