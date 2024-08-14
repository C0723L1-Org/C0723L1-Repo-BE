package org.c07.movie_booking.service.implement;
import org.c07.movie_booking.dto.KindOfFilmDTO;
import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.exception.FieldRequiredException;
import org.c07.movie_booking.model.KindOfFilm;
import org.c07.movie_booking.model.Movie;
import org.c07.movie_booking.repository.IMovieRepository;
import org.c07.movie_booking.service.IMovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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

//    public Page<MovieDTO> getFindAll(Integer pageNumber, Integer pageSize) {
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        Page<Movie> movieEntity = iMovieRepository.findAllByQuery(pageable);
//        List<MovieDTO> movieDTOList = movieEntity.stream()
//                .map(movie -> {
//                    MovieDTO movieDTO = modelMapper.map(movie , MovieDTO.class);
//                    List<String> kindOfFilmNames = movie.getKindOfFilms().stream()
//                            .map(KindOfFilm::getName)
//                            .collect(Collectors.toList());
//                    // Cập nhật danh sách tên thể loại vào MovieDTO
//                    movieDTO.setKindOfFilms(kindOfFilmNames);
//                    return movieDTO;
//                }).collect(Collectors.toList());
//
//        return new PageImpl<>(movieDTOList, pageable, movieEntity.getTotalPages());
//    }

    public Page<MovieDTO> getFindAll(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Movie> movieEntity = iMovieRepository.findAllByQuery(pageable);
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for (Movie movie: movieEntity){
            MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
            List<KindOfFilmDTO> kindOfFilmDTOS = new ArrayList<>();
            for (KindOfFilm kind: movie.getKindOfFilms()){
                KindOfFilmDTO kindOfFilmDTO = modelMapper.map(kind, KindOfFilmDTO.class);
                kindOfFilmDTOS.add(kindOfFilmDTO);
            }
            movieDTO.setKindOfFilm(kindOfFilmDTOS);
            movieDTOList.add(movieDTO);
        }

        return new PageImpl<>(movieDTOList, pageable, movieEntity.getTotalPages());
    }

    @Override
    public Page<MovieDTO> getSearchFields(String nameMovie, String content, String director,
                                          LocalDate releaseDate, String nameStatus, String actor,
                                          Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Movie> movies = iMovieRepository.getSearchOfFields(nameMovie, content, director,
                releaseDate, nameStatus, actor, pageable);
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for (Movie mv: movies){
            MovieDTO movieDTO = modelMapper.map(mv, MovieDTO.class);
            List<KindOfFilmDTO> kindOfFilmDTOS = new ArrayList<>();
            for (KindOfFilm kind: mv.getKindOfFilms()){
                KindOfFilmDTO kindOfFilmDTO = modelMapper.map(kind, KindOfFilmDTO.class);
                kindOfFilmDTOS.add(kindOfFilmDTO);
            }
            movieDTO.setKindOfFilm(kindOfFilmDTOS);
            movieDTOList.add(movieDTO);
        }

        return new PageImpl<>(movieDTOList, pageable, movies.getTotalPages());
    }
//@Override
//public Page<MovieDTO> getSearchFields(String nameMovie, String content, String director,
//                                      LocalDate releaseDate, String nameStatus, String actor,
//                                      Integer pageNumber, Integer pageSize) {
//    Pageable pageable = PageRequest.of(pageNumber, pageSize);
//    Page<Movie> movies = iMovieRepository.getSearchOfFields(nameMovie, content, director,
//            releaseDate, nameStatus, actor, pageable);
//    List<MovieDTO> movieDTOList = new ArrayList<>();
//    for (Movie mv: movies){
//        MovieDTO movieDTO = modelMapper.map(mv, MovieDTO.class);
//        List<String> kinds = new ArrayList<>();
//        for (KindOfFilm kf: mv.getKindOfFilms()){
//            kinds.add(kf.getName());
//        }
//        movieDTO.setKindOfFilms(kinds);
//        movieDTOList.add(movieDTO);
//    }
//
//    return new PageImpl<>(movieDTOList, pageable, movies.getTotalPages());
//}

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
