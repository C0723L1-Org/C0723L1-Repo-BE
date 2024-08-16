package org.c07.movie_booking.service;

import org.c07.movie_booking.model.Seat;

import java.util.List;

public interface ISeatService {
    List<Seat> getAllSelectedSeat(Long showtimeId);

    Seat getSeatByRoomIdAndSeatNumber(Long roomId, String seatNumber);
}
