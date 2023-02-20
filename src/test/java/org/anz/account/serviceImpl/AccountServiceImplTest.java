package org.anz.account.serviceImpl;

import org.anz.account.common.TestConstants;
import org.anz.account.dto.AccountDTO;
import org.anz.account.exception.AccountServiceException;
import org.anz.account.helper.TestHelper;
import org.anz.account.mapper.AccountMapper;
import org.anz.account.model.Account;
import org.anz.account.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    @Test
    public void testGetAccountsByUserProfileIdSuccess() throws AccountServiceException {
        Account account1 = TestHelper.getAccount1();
        AccountDTO accountDTO1 = TestHelper.getAccountDTO1();
        Stream<Account> accountStream = Stream.of(account1);
        Mockito.when(accountRepository.findByuserProfileId(1L)).thenReturn(accountStream);
        Mockito.when(accountMapper.accountToAccountDTO(account1)).thenReturn(accountDTO1);
        List<AccountDTO> accountDTOList = accountServiceImpl.getAccountsByUserProfileId(1L);
        AccountDTO actualAccountDTO = accountDTOList.stream().findAny().get();
        Assertions.assertEquals(accountDTO1.getAccountNumber(), actualAccountDTO.getAccountNumber());
        Assertions.assertEquals(accountDTO1.getAccountName(), actualAccountDTO.getAccountName());
        Assertions.assertEquals(accountDTO1.getAccountType(), actualAccountDTO.getAccountType());
        Assertions.assertEquals(accountDTO1.getCurrency(), actualAccountDTO.getCurrency());
        Assertions.assertEquals(accountDTO1.getBalanceDate(), actualAccountDTO.getBalanceDate());
        Assertions.assertEquals(accountDTO1.getOpeningAvailableBalance(), actualAccountDTO.getOpeningAvailableBalance());
    }

    @Test
    public void testGetAccountsByUserProfileIdWhenUserProfileNotPresent() throws AccountServiceException {
        Mockito.when(accountRepository.findByuserProfileId(1L)).thenReturn(Stream.empty());
        List<AccountDTO> accountDTOList = accountServiceImpl.getAccountsByUserProfileId(1L);
        Assertions.assertEquals(List.of().size(), accountDTOList.size());
    }

    @Test
    public void testGetAccountsByUserProfileIdDuringDataAccessException() throws AccountServiceException {

        Mockito.when(accountRepository.findByuserProfileId(1L)).thenThrow(new DataAccessException(TestConstants.TESTING) {});
        AccountServiceException exception = Assertions.assertThrows(AccountServiceException.class,
                () -> accountServiceImpl.getAccountsByUserProfileId(1L));;
        Assertions.assertEquals(exception.getMessage(), TestConstants.TESTING);
    }
}
