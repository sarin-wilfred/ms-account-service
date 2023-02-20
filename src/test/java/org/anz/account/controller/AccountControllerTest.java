package org.anz.account.controller;

import org.anz.account.dto.AccountDTO;
import org.anz.account.exception.AccountServiceException;
import org.anz.account.helper.TestHelper;
import org.anz.account.model.Account;
import org.anz.account.serviceImpl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    @Mock
    private AccountServiceImpl accountServiceImpl;

    @InjectMocks
    private AccountController accountController;

    @Test
    public void testGetAccountsByUserProfileIdSuccess() throws AccountServiceException {
        AccountDTO accountDTO1 = TestHelper.getAccountDTO1();
        List<AccountDTO> accountList = List.of(accountDTO1);
        Mockito.when(accountServiceImpl.getAccountsByUserProfileId(1L)).thenReturn(accountList);
        ResponseEntity<List<AccountDTO>> accountDTOListResp = accountController.getAccountsByUserProfileId(1L);
        Assertions.assertEquals(accountDTO1, accountDTOListResp.getBody().stream().findAny().get());
    }

    @Test
    public void testGetAccountsByUserProfileIdWithEmptyResult() throws AccountServiceException {
        AccountDTO accountDTO1 = TestHelper.getAccountDTO1();
        Mockito.when(accountServiceImpl.getAccountsByUserProfileId(1L)).thenReturn(List.of());
        ResponseEntity<List<AccountDTO>> accountDTOListResp = accountController.getAccountsByUserProfileId(1L);
        Assertions.assertEquals(List.of().size(), accountDTOListResp.getBody().size());
    }

}
