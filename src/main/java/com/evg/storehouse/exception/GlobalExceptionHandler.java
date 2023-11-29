package com.evg.storehouse.exception;

import com.evg.otus.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.evg.otus.enums.ErrorMessageCode.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j(topic = "EXCEPTION_HANDLER")
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DataNotFoundException.class, BadRequestException.class})
    public BaseResponse dataNotFoundException(StorehouseException e) {
        LOGGER.error("dataNotFoundException {}", e);
        return new BaseResponse(e.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Throwable.class})
    public BaseResponse exceptions(Exception e) {
        LOGGER.error("exception {}", e);
        return new BaseResponse(INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({BadCredentialsException.class})
    public BaseResponse exceptions(BadCredentialsException e) {
        LOGGER.error("badCredentialsException {}", e);
        return new BaseResponse(HttpStatus.FORBIDDEN.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({MissingRequestHeaderException.class})
    public BaseResponse exceptions(MissingRequestHeaderException e) {
        LOGGER.error("missingRequestHeaderException {}", e);
        return new BaseResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

}
