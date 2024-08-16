package org.c07.movie_booking.service.implement;

import org.c07.movie_booking.model.Seat;
import org.c07.movie_booking.repository.ISeatRepository;
import org.c07.movie_booking.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService implements ISeatService {
    @Autowired
    ISeatRepository seatRepository;
    @Override
    public List<Seat> getAllSelectedSeat(Long showtimeId) {
        return seatRepository.getAllSelectedSeat(showtimeId);
    }

    @Override
    public Seat getSeatByRoomIdAndSeatNumber(Long roomId, String seatNumber) {
        return seatRepository.getSeatByRoomIdAndSeatNumber(roomId,seatNumber);
    }
}
