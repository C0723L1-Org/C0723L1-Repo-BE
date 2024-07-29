package org.c07.movie_booking.service;

import org.c07.movie_booking.dto.StatusFilmDTO;

import java.util.List;

public interface IStatusFilmService {
    List<StatusFilmDTO> getFindAll();
}
