package org.anz.account.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.anz.account.dto.AccountDTO;
import org.anz.account.dto.ApiError;
import org.anz.account.exception.AccountServiceException;
import org.anz.account.service.AccountService;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    /**
     * This method is used to get accounts by user profile id
     *
     * @param userProfileId
     * @return ResponseEntity<List<AccountDTO>>
     * @throws AccountServiceException
     */
    @PostMapping(value = "/api/accounts/{userProfileId}")
    public ResponseEntity<List<AccountDTO>> getAccountsByUserProfileId(@PathVariable("userProfileId") Long  userProfileId) throws AccountServiceException {
        log.info("User profile id: {}", userProfileId);
        List<AccountDTO> accountDTOList = accountService.getAccountsByUserProfileId(userProfileId);
        log.info("Number of accounts returned: {}", accountDTOList.size());
        return ResponseEntity.ok()
                .body(accountDTOList);
    }

    /**
     * Method added to handle NumberFormatException
     *
     * @param request
     * @param nfEx
     * @return ResponseEntity<ApiError>
     */
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ApiError> handleException(HttpServletRequest request, NumberFormatException nfEx) {
        log.error("Get accounts error: {}", nfEx.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .cacheControl(CacheControl.noCache())
                .body(ApiError.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message("The user profile is not valid.")
                        .build());
    }
}
