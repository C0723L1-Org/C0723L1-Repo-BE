package org.c07.movie_booking.service.implement;

import org.c07.movie_booking.dto.StatusFilmDTO;
import org.c07.movie_booking.model.StatusFilm;
import org.c07.movie_booking.repository.IStatusFilmRepository;
import org.c07.movie_booking.service.IStatusFilmService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class StatusFilmService implements IStatusFilmService {
    @Autowired
    private IStatusFilmRepository iStatusFilmRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<StatusFilmDTO> getFindAll() {
        List<StatusFilm> statusFilms = iStatusFilmRepository.findAll();
        List<StatusFilmDTO> statusFilmDTOList = new ArrayList<>();
        for(StatusFilm items: statusFilms){
            StatusFilmDTO statusFilmDTO = modelMapper.map(items, StatusFilmDTO.class);
            statusFilmDTOList.add(statusFilmDTO);
        }
        return statusFilmDTOList;
    }

    @Override
    public List<StatusFilm> getStatusMovie() {
        return iStatusFilmRepository.getMovieStatusFilm();
    }
}
