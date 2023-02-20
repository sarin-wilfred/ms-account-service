package org.anz.account.service;

import org.anz.account.dto.AccountTransactionDTO;
import org.anz.account.exception.AccountServiceException;

import java.util.List;

public interface AccountTransactionService {

    public List<AccountTransactionDTO> getAccountTransactionsByAccountNumber(Long accountNumber) throws AccountServiceException;

}
