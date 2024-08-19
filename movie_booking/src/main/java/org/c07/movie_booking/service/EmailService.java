package org.c07.movie_booking.service;

import com.google.zxing.WriterException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import org.c07.movie_booking.qrcode.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.IOException;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendTicketEmail(String to, String subject, String ticketCode, String movieName, String cinema, String room, String showtime, String seats, String price, String totalPrice) throws  IOException, MessagingException, WriterException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);
        QRCodeService qrCodeService = new QRCodeService();
        String qrCodeText = "Mã vé: " + ticketCode + ", Phim: " + movieName + ", Rạp: " + cinema + ", Suất: " + showtime + ", Ghế: " + seats;
//        String qrCodeImage = "data:image/png;base64," + qrCodeBase64;
        byte[] qrCodeImage = qrCodeService.generateQRCodeImage(qrCodeText, 100, 100);
        String img = "<img src='cid:qrcode'/>";
        // Load the HTML template and replace placeholders
        String htmlContent = new String(getClass().getResourceAsStream("/ticket-template.html").readAllBytes());
        htmlContent = htmlContent.replace("{{ ticketCode }}", ticketCode);
        htmlContent = htmlContent.replace("{{ movieName }}", movieName);
        htmlContent = htmlContent.replace("{{ cinema }}", cinema);
        htmlContent = htmlContent.replace("{{ room }}", room);
        htmlContent = htmlContent.replace("{{ showtime }}", showtime);
        htmlContent = htmlContent.replace("{{ seats }}", seats);
        htmlContent = htmlContent.replace("{{ price }}", price);
        htmlContent = htmlContent.replace("{{ totalPrice }}", totalPrice);
        htmlContent = htmlContent.replace("{{ img }}", img);
        helper.setText(htmlContent, true);
        helper.addInline("qrcode", new ByteArrayDataSource(qrCodeImage, "image/png"));
        System.out.println(img);
        mailSender.send(message);
    }
}