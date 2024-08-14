package org.c07.movie_booking.controller;

import jakarta.validation.Valid;
import org.c07.movie_booking.dto.BookingDTO;
import org.c07.movie_booking.dto.response.BookingResDTO;
import org.c07.movie_booking.model.Booking;
import org.c07.movie_booking.service.EmailService;
import org.c07.movie_booking.service.IBookingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    @Autowired
    IBookingService iBookingService;
    @Autowired
    EmailService emailService;


//    @PostMapping("/create")
//    public ResponseEntity<?> createNewBooking(@Valid @RequestBody BookingDTO bookingDto,
//                                              BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            Map<String, String> map = new HashMap<>();
//            bindingResult.getFieldErrors().forEach(error -> {
//                map.put(error.getField(), error.getDefaultMessage());
//            });
//            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
//        }
//        bookingDto.setCodeBooking(iBookingService.createCodeBooking());
//        Booking booking = new Booking();
//        BeanUtils.copyProperties(bookingDto,booking);
//        String msg;
//        if(iBookingService.addNewBooking(booking)){
//            sendEmail(booking.getUser().getEmail(),
//                    booking.getCodeBooking(),
//                    booking.getShowTime().getMovie().getNameMovie(),
//                    booking.getShowTime().getRoom().getRoomName(),
//                    booking.getShowTime().getShowDate().toString(),
//                    booking.getSeat().getSeatNumber(),
//                    Integer.toString(booking.getSeat().getPrice()),
//                    Double.toString(booking.getTotalAmount()));
//            msg ="Booking success";
//            return new ResponseEntity<>(msg,HttpStatus.OK);
//        } else {
//            msg = "Failed";
//            return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
//        }
//    }
//    private void sendEmail(String to, String ticketCode,
//                           String movieName, String room,
//                           String showtime, String seats, String price, String totalPrice){
//        try{
//            emailService.sendTicketEmail(
//                    to,
//                    "Thông tin vé của bạn",
//                    ticketCode,
//                    movieName,
//                    "CGV Vinh Trung Plaza",
//                    room,
//                    showtime,
//                    seats,
//                    price,
//                    totalPrice);
//        } catch (Exception ignored){
//            System.out.println(ignored);
//        }
//    }


    // Danh sách vé của riêng từng khách hàng
    @GetMapping("private/booking-customer")
    public ResponseEntity<List<BookingDTO>> showList() {
        List<BookingDTO> bookings = iBookingService.fillAllBooking();
        return ResponseEntity.ok(bookings);
    }
    // Show danh sách vé chưa nhận
    @GetMapping("private/list-booking")
    public ResponseEntity<Page<BookingResDTO>> SearchBookings(
            @RequestParam(value = "valueSearch", defaultValue = "") String valueSearch,
            @RequestParam("page") Optional<Integer> page) {
        System.out.println("ValueSearch: " + valueSearch);
        int currentPage = page.map(p -> Math.max(p, 0)).orElse(0);
        Pageable pageable = PageRequest.of(currentPage, 5);
        Page<BookingResDTO> bookings = iBookingService.SearchBookings(
                valueSearch, pageable);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
    // Tìm vé theo Id
    @GetMapping("private/booking-details/{id}")
    public ResponseEntity<BookingResDTO> findEmployeeById(@PathVariable Long id) {
        BookingResDTO bookingResDTO = iBookingService.findBookingResDTOById(id);
        return ResponseEntity.ok(bookingResDTO);
    }
    // Nhận vé
    @PutMapping("private/receive-booking/{id}")
    public ResponseEntity<String> receiveBookingById(@PathVariable Long id) {
        BookingResDTO bookingResDTO = iBookingService.findBookingResDTOById(id);
        if (bookingResDTO == null) {
            return new ResponseEntity<>("Booking not found to receive !", HttpStatus.BAD_REQUEST);
        }
        iBookingService.receiveBookingById(id);
        return new ResponseEntity<>("Received Booking successfully.", HttpStatus.OK);
    }
}
