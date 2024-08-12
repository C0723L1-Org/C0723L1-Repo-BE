package org.c07.movie_booking.service;

import org.c07.movie_booking.dto.KindOfFilmDTO;

import java.util.List;

public interface IKindOfFilmService {
    List<KindOfFilmDTO> getFindAll();
}
