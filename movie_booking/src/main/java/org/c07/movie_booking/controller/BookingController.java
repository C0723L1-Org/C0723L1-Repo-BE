package org.c07.movie_booking.controller;

import jakarta.validation.Valid;
import org.c07.movie_booking.dto.BookingDto;
import org.c07.movie_booking.dto.response.BookingDetailResDTO;
import org.c07.movie_booking.dto.response.BookingResDTO;
import org.c07.movie_booking.model.Booking;
import org.c07.movie_booking.model.BookingStatus;
import org.c07.movie_booking.model.auth_entity.BookingRequest;
import org.c07.movie_booking.service.EmailService;
import org.c07.movie_booking.service.IBookingService;
import org.c07.movie_booking.service.IUserService;
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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    @Autowired
    IBookingService bookingService;
    @Autowired
    EmailService emailService;
    @Autowired
    IUserService userService;
    // tạo mới booking
    @PostMapping("/create/{id}")
    public ResponseEntity<?> createNewBooking(@Valid @RequestBody BookingDto bookingDto,
                                              @PathVariable Long id,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> map = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                map.put(error.getField(), error.getDefaultMessage());
            });
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        bookingDto.setCodeBooking(bookingService.createCodeBooking());
        Booking booking = new Booking();
        BeanUtils.copyProperties(bookingDto,booking);
        booking.setUser(userService.findUserById(id));
        String msg;
        System.out.println(booking);
        if(bookingService.addNewBooking(booking)){
            // sau 5 phút kiểm tra lại xem booking vừa xem đc thanh toán chưa ?
            // nếu chưa thì set status  = đã hủy booking id status =3
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                // Lên lịch xóa booking sau 1 phút (600 giây)
            scheduler.schedule(() -> changeStatusBookingToCancel(booking.getSeat().getId(),booking.getShowTime().getId()), 10, TimeUnit.MINUTES);
            msg ="Create Booking success";
            System.out.println(msg);
            return new ResponseEntity<>(msg,HttpStatus.OK);
        }
            return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);

    }
    private void changeStatusBookingToCancel(Long seatId,Long showtimeId){
        Booking booking = bookingService.findBookingBySeatIdAndShowtimeId(seatId,showtimeId);
        if(booking.getBookingStatus().getId() == 1){
            BookingStatus bookingStatus =new BookingStatus();
            bookingStatus.setId(3L);
            bookingStatus.setName("Hủy đặt vé");
            booking.setBookingStatus(bookingStatus);
            // vì cùng ID nên JPA sẽ update;
            if(bookingService.addNewBooking(booking)){
                System.out.println("cập nhập thành công");
            }
        }

    }
    @PostMapping("/update")
    public ResponseEntity<?> updateBooking(@RequestBody BookingRequest bookingRequest){
        Booking booking = bookingService.findBookingBySeatIdAndShowtimeId(bookingRequest.getSeatId(),bookingRequest.getShowtimeId());
        BookingStatus bookingStatus =new BookingStatus();
        bookingStatus.setId(2L);
        bookingStatus.setName("Đã thanh toán");
        booking.setBookingStatus(bookingStatus);
        // sau khi thanh toán thì
        if(bookingService.addNewBooking(booking)){
            sendEmail(booking.getUser().getEmail(),
                    booking.getCodeBooking(),
                    booking.getShowTime().getMovie().getNameMovie(),
                    booking.getShowTime().getRoom().getRoomName(),
                    booking.getShowTime().getShowDate().toString(),
                    booking.getSeat().getSeatNumber(),
                    Integer.toString(booking.getSeat().getPrice()),
                    Double.toString(booking.getTotalAmount()));
            return new ResponseEntity<>("Thanh toán thành công",HttpStatus.OK);
        }else  {

            return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
        }
    }
    private void sendEmail(String to, String ticketCode,
                           String movieName, String room,
                           String showtime, String seats, String price, String totalPrice){
        try{
            emailService.sendTicketEmail(
                    to,
                    "Thông tin vé của bạn",
                    ticketCode,
                    movieName,
                    "CGV Vinh Trung Plaza",
                    room,
                    showtime,
                    seats,
                    price,
                    totalPrice);
        } catch (Exception ignored){
            System.out.println(ignored);
        }
    }

    // Danh sách vé của riêng từng khách hàng
    @GetMapping("private/list-booking")
    public ResponseEntity<Page<BookingResDTO>> SearchBookings(
            @RequestParam(value = "valueSearch", defaultValue = "") String valueSearch,
            @RequestParam("page") Optional<Integer> page) {
        System.out.println("ValueSearch: " + valueSearch);
        int currentPage = page.map(p -> Math.max(p, 0)).orElse(0);
        Pageable pageable = PageRequest.of(currentPage, 5);
        Page<BookingResDTO> bookings = bookingService.SearchBookings(
                valueSearch, pageable);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
    // Tìm vé theo Id
    @GetMapping("private/booking-details/{id}")
    public ResponseEntity<BookingResDTO> findEmployeeById(@PathVariable Long id) {
        BookingResDTO bookingResDTO = bookingService.findBookingResDTOById(id);
        return ResponseEntity.ok(bookingResDTO);
    }
    // Nhận vé
    @PutMapping("private/receive-booking/{id}")
    public ResponseEntity<String> receiveBookingById(@PathVariable Long id) {
        BookingResDTO bookingResDTO = bookingService.findBookingResDTOById(id);
        if (bookingResDTO == null) {
            return new ResponseEntity<>("Booking not found to receive !", HttpStatus.BAD_REQUEST);
        }
        bookingService.receiveBookingById(id);
        return new ResponseEntity<>("Received Booking successfully.", HttpStatus.OK);
    }

    //     Danh sách vé của riêng từng khách hàng
    @GetMapping("private/booking-customer")
    public ResponseEntity<?> getBookingDetails() {
        List<BookingDetailResDTO> bookingDetails = bookingService.getBookingDetails();
        return ResponseEntity.ok(bookingDetails);
    }
}






