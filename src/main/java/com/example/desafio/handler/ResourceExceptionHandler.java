package com.example.desafio.handler;

import com.example.desafio.exception.NotFoundException;
import com.example.desafio.exception.UnprocessableException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ResourceExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseMessageError> handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        return popularResponseMessageError(e, HttpStatus.NOT_FOUND.value(), request);
    }

    @ExceptionHandler(UnprocessableException.class)
    public ResponseEntity<ResponseMessageError> handleUnprocessableException(UnprocessableException e, HttpServletRequest request) {
        return popularResponseMessageError(e, HttpStatus.UNPROCESSABLE_ENTITY.value(), request);
    }

    private static ResponseEntity<ResponseMessageError> popularResponseMessageError(Exception e, Integer status,
                                                                                    HttpServletRequest request) {
        ResponseMessageError err = new ResponseMessageError();
        err.setDateTime(LocalDateTime.now());
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}