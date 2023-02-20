package org.anz.account.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.anz.account.dto.ApiError;
import org.anz.account.exception.AccountServiceException;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class HandleException {
    /**
     * Method added to handle AccountServiceException
     *
     * @param request
     * @param acSerEx
     * @return ResponseEntity<ApiError>
     */
    @ExceptionHandler(AccountServiceException.class)
    public ResponseEntity<ApiError> handleException(HttpServletRequest request, AccountServiceException acSerEx) {
        log.error("Service error: {}", acSerEx.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiError.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message("Exception occurred during internal operation")
                        .build());
    }

    /**
     * Method added to handle Exception
     *
     * @param request
     * @param ex
     * @return ResponseEntity<ApiError>
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(HttpServletRequest request, Exception ex) {
        log.error("Error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ApiError.builder()
                        .status(HttpStatus.METHOD_NOT_ALLOWED)
                        .message(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase())
                        .build());
    }
}
