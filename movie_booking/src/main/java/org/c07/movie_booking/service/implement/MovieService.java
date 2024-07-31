package org.c07.movie_booking.service.implement;

import org.c07.movie_booking.dto.ErrorResponseDTO;
import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.exception.FieldRequiredException;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.repository.IMovieRepository;
import org.c07.movie_booking.service.IMovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @Override
    public Page<Movie> getSearchMovieByNameMovie(String searchContent, Pageable pageable) {
        return iMovieRepository.getSearchMovieByNameMovie(searchContent, pageable);
    }

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
