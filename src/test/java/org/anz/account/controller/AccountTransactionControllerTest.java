package org.anz.account.controller;

import org.anz.account.dto.AccountDTO;
import org.anz.account.dto.AccountTransactionDTO;
import org.anz.account.exception.AccountServiceException;
import org.anz.account.helper.TestHelper;
import org.anz.account.serviceImpl.AccountTransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AccountTransactionControllerTest {

    @Mock
    private AccountTransactionServiceImpl accountTransactionServiceImpl;

    @InjectMocks
    private AccountTransactionController accountTransactionController;

    @Test
    public void testGAccountTransactionsByAccountNumberSuccess() throws AccountServiceException {
        AccountTransactionDTO accountTransactionDTO1 = TestHelper.getAccountTransactionDTO1();
        List<AccountTransactionDTO> accountList = List.of(accountTransactionDTO1);
        Mockito.when(accountTransactionServiceImpl.getAccountTransactionsByAccountNumber(1L)).thenReturn(accountList);
        ResponseEntity<List<AccountTransactionDTO>> accountTransactionDTOListResp = accountTransactionController.getAccountTransactionsByAccountNumber(1L);
        Assertions.assertEquals(accountTransactionDTO1, accountTransactionDTOListResp.getBody().stream().findFirst().get());
    }

    @Test
    public void testGAccountTransactionsByAccountNumberWithEmptyResult() throws AccountServiceException {
        AccountTransactionDTO accountDTO1 = TestHelper.getAccountTransactionDTO1();
        Mockito.when(accountTransactionServiceImpl.getAccountTransactionsByAccountNumber(1L)).thenReturn(List.of());
        ResponseEntity<List<AccountTransactionDTO>> accountTransactionDTOListResp = accountTransactionController.getAccountTransactionsByAccountNumber(1L);
        Assertions.assertEquals(List.of().size(), accountTransactionDTOListResp.getBody().size());
    }
}
