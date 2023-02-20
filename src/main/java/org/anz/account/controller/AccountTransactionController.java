package org.anz.account.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.anz.account.dto.AccountTransactionDTO;
import org.anz.account.dto.ApiError;
import org.anz.account.exception.AccountServiceException;
import org.anz.account.service.AccountTransactionService;
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
public class AccountTransactionController {

    private final AccountTransactionService accountTransactionService;

    /**
     * This method is used to get account transactions by account number
     *
     * @param accountNumber
     * @return ResponseEntity<List<AccountTransactionDTO>>
     * @throws AccountServiceException
     */
    @PostMapping(value = "/api/accountTransactions/{accountNumber}")
    public ResponseEntity<List<AccountTransactionDTO>> getAccountTransactionsByAccountNumber(@PathVariable("accountNumber") Long accountNumber) throws AccountServiceException {
        log.info("Account number: {}", accountNumber);
        List<AccountTransactionDTO> accountTransactionDTOList = accountTransactionService.getAccountTransactionsByAccountNumber(accountNumber);
        log.info("Number of account transactions returned: {}", accountTransactionDTOList.size());
        return ResponseEntity.ok()
                .body(accountTransactionDTOList);
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
                .body(ApiError.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message("The account is not valid.")
                        .build());
    }
}
