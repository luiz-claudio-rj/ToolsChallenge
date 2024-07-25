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

/**
 * Classe que trata as exceções da aplicação
 */
@ControllerAdvice
@RequiredArgsConstructor
public class ResourceExceptionHandler {
    /**
     * Trata exceções do tipo NotFoundException
     *
     * @param e       NotFoundException
     * @param request HttpServletRequest
     * @return ResponseEntity<ResponseMessageError>
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseMessageError> handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        return popularResponseMessageError(e, HttpStatus.NOT_FOUND.value(), request);
    }

    /**
     * Trata exceções do tipo UnprocessableException
     *
     * @param e       UnprocessableException
     * @param request HttpServletRequest
     * @return ResponseEntity<ResponseMessageError>
     */
    @ExceptionHandler(UnprocessableException.class)
    public ResponseEntity<ResponseMessageError> handleUnprocessableException(UnprocessableException e, HttpServletRequest request) {
        return popularResponseMessageError(e, HttpStatus.UNPROCESSABLE_ENTITY.value(), request);
    }

    /**
     * Método que popula o objeto ResponseMessageError
     *
     * @param e       Exception
     * @param status  Integer
     * @param request HttpServletRequest
     * @return ResponseEntity<ResponseMessageError>
     */
    private static ResponseEntity<ResponseMessageError> popularResponseMessageError(Exception e, Integer status,
                                                                                    HttpServletRequest request) {
        ResponseMessageError err = new ResponseMessageError();
        err.setDateTime(LocalDateTime.now());
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}