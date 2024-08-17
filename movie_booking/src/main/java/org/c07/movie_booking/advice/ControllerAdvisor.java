package org.c07.movie_booking.advice;

import org.c07.movie_booking.dto.ErrorResponseDTO;
import org.c07.movie_booking.exception.FieldRequiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(FieldRequiredException.class)
    public ResponseEntity<Object> handleFieldRequiredException(FieldRequiredException ex, WebRequest request){
        ErrorResponseDTO response = new ErrorResponseDTO();
        response.setError(ex.getMessage());
        List<String> detail = new ArrayList<>();
        detail.add("Check lại id đi vì đang bị null hoặc giá trị không có trong dữ liệu đó");
        response.setDetail(detail);
        return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
    }
}