package org.anz.account.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public class ApiError {
    private HttpStatus status;
    private String message;
}
