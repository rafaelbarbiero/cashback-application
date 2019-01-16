package br.com.rbarbiero.application.handler;

import br.com.rbarbiero.exception.GenreNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Optional;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> httpMessageNotReadableException(MethodArgumentTypeMismatchException ex) {
        final HttpStatus responseStatus = Optional.ofNullable(ex.getClass().getAnnotation(ResponseStatus.class))
                .map(ResponseStatus::value)
                .orElse(HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(responseStatus)
                .headers(new HttpHeaders())
                .body(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GenreNotFoundException.class)
    public ResponseEntity<Object> badRequestException(GenreNotFoundException ex) {
        final HttpStatus responseStatus = Optional.ofNullable(ex.getClass().getAnnotation(ResponseStatus.class))
                .map(ResponseStatus::value)
                .orElse(HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(responseStatus)
                .headers(new HttpHeaders())
                .body(ex.getMessage());
    }
}
