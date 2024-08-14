package org.c07.movie_booking.service.implement;

import org.c07.movie_booking.dto.response.BookingDetailResDTO;
import org.c07.movie_booking.dto.response.BookingResDTO;
import org.c07.movie_booking.model.Booking;
import org.c07.movie_booking.repository.IBookingRepository;
import org.c07.movie_booking.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookingService implements IBookingService {
    @Autowired
    IBookingRepository bookingRepository;
    @Override
    public boolean addNewBooking(Booking booking) {
        try{
            bookingRepository.save(booking);
            return true;
        }
        catch (Exception e){ return  false;}
    }

    @Override
    public String createCodeBooking() {
        // lay danh sach ra
        List<Booking> bookingList = bookingRepository.findAll();
        if (bookingList.isEmpty()){
            return "1";
        } else {
            int currentCode = Integer.parseInt(bookingRepository.selectCurrentCode());
            return Integer.toString(currentCode +1);
        }
    }

    // Danh sách vé của riêng từng khách hàng
    @Override
    public List<BookingDetailResDTO> getBookingDetails() {
        return bookingRepository.findBookingDetails();
    }

    // Danh sách vé chưa in- role nhân viên hoặc admin
    @Override
    public Page<BookingResDTO> SearchBookings(String valueSearch, Pageable pageable) {
        return bookingRepository.SearchBookings(valueSearch, pageable) ;
    }

    // Tìm vé và xem chi tiết vé theo Id
    @Override
    public BookingResDTO findBookingResDTOById(Long id) {
        BookingResDTO bookingResDTO = bookingRepository.findBookingResDTOById(id).orElse(null);
        if (bookingResDTO == null || bookingResDTO.getReceive()) {
            return null;
        } else {
            return bookingResDTO;
        }
    }

    // Nhận vé
    @Override
    public void receiveBookingById(Long id) {
        bookingRepository.receiveBookingById(id);
    }
}
