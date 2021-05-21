package ru.savinov.app.exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.savinov.app.controller.dto.StatusResponseDto;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler
    public ResponseEntity handleDefault(Exception e) {
        log.warn(getStackTrace(e));
        return new ResponseEntity(StatusResponseDto.of("FAILURE"), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(VisitFormatException.class)
    public ResponseEntity handleDefault(VisitFormatException e) {
        log.warn(getStackTrace(e));
        return new ResponseEntity(StatusResponseDto.of("FAILURE"), HttpStatus.BAD_REQUEST);
    }

    private static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
}