package edu.fekrat.music_api.exceptions;

import edu.fekrat.music_api.dtos.ApiExceptionDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiExceptionDto> handleNotFoundException(
            NotFoundException e, HttpServletRequest request) {
        ApiExceptionDto dto = new ApiExceptionDto(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                request.getRequestURI(),
                e.getClass().getSimpleName()
        );
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiExceptionDto> handleAlreadyExistsException(
            AlreadyExistsException e, HttpServletRequest request) {
        ApiExceptionDto dto = new ApiExceptionDto(
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                request.getRequestURI(),
                e.getClass().getSimpleName()
        );
        return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionDto> handleGenericException(
            Exception e, HttpServletRequest request) {
        ApiExceptionDto dto = new ApiExceptionDto(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getRequestURI(),
                e.getClass().getSimpleName()
        );
        return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}