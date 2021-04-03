package fr.uge.lootin.back.controllers;

import fr.uge.lootin.back.dto.ErrorResponse;
import fr.uge.lootin.back.exception.BadRequestException;
import fr.uge.lootin.back.exception.ForbiddenException;
import fr.uge.lootin.back.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    ErrorResponse forbidden(ForbiddenException ex) {
        return ex.toErrorResponse();
    }

    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse badRequest(BadRequestException ex) {
        return ex.toErrorResponse();
    }

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse notFound(NotFoundException ex) {
        return ex.toErrorResponse();
    }
}
