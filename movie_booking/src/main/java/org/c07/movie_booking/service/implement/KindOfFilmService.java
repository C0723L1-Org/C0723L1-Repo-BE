package org.c07.movie_booking.service.implement;

import org.c07.movie_booking.dto.KindOfFilmDTO;
import org.c07.movie_booking.model.KindOfFilm;
import org.c07.movie_booking.repository.IKindOfFilmRepository;
import org.c07.movie_booking.service.IKindOfFilmService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class KindOfFilmService implements IKindOfFilmService {
    @Autowired
    private IKindOfFilmRepository iKindOfFilmRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<KindOfFilmDTO> getFindAll() {
        List<KindOfFilm> kindOfFilms = iKindOfFilmRepository.findAllByQuery();
        List<KindOfFilmDTO> kindOfFilmDTOS = new ArrayList<>();
        for (KindOfFilm kindOfFilm: kindOfFilms){
            KindOfFilmDTO kindOfFilmDTO = modelMapper.map(kindOfFilm, KindOfFilmDTO.class);
            kindOfFilmDTOS.add(kindOfFilmDTO);
        }
        return kindOfFilmDTOS;
    }
    @Override
    public List<KindOfFilm> getKindOfMovie() {
        return iKindOfFilmRepository.getMovieKindOfMovie();
    }
}
