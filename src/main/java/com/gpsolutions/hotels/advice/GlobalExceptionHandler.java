package com.gpsolutions.hotels.advice;

import com.gpsolutions.hotels.dto.response.ErrorResponse;
import com.gpsolutions.hotels.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(HttpServletRequest request, MethodArgumentNotValidException e) {
        log.warn("Data is not valid", e);
        String messages = e.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));
        return new ErrorResponse().setStatus(HttpStatus.BAD_REQUEST.value())
                .setError(messages)
                .setPath(request.getRequestURI());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEntityNotFoundException(HttpServletRequest request, EntityNotFoundException e) {
        log.warn("Entity not found", e);
        return new ErrorResponse().setStatus(HttpStatus.NOT_FOUND.value())
                .setError(e.getMessage())
                .setPath(request.getRequestURI());
    }
}
