package org.c07.movie_booking.service.implement;

import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.repository.IMovieRepository;
import org.c07.movie_booking.service.IMovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class MovieServiceImpl implements IMovieService {
    @Autowired
    private IMovieRepository movieRepository;

    @Override
    public Page<Movie> getSearchMovieByNameMovie(String searchContent, Pageable pageable) {
        return movieRepository.getSearchMovieByNameMovie(searchContent, pageable);
    }

}
