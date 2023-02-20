package org.anz.account.serviceImpl;

import org.anz.account.common.TestConstants;
import org.anz.account.dto.AccountTransactionDTO;
import org.anz.account.exception.AccountServiceException;
import org.anz.account.helper.TestHelper;
import org.anz.account.mapper.AccountTransactionMapper;
import org.anz.account.model.AccountTransaction;
import org.anz.account.repository.AccountTransactionRepository;
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
public class AccountTransactionServiceImplTest {

    @Mock
    private AccountTransactionRepository accountTransactionRepository;

    @Mock
    private AccountTransactionMapper accountTransactionMapper;

    @InjectMocks
    private AccountTransactionServiceImpl accountTransactionServiceImpl;

    @Test
    public void testGetAccountTransactionsByAccountNumberSuccess() throws AccountServiceException {
        AccountTransaction accountTransaction1 = TestHelper.getAccountTransaction1();
        AccountTransactionDTO accountTransactionDTO1 = TestHelper.getAccountTransactionDTO1();
        Stream<AccountTransaction> accountTransactionStream = Stream.of(accountTransaction1);
        Mockito.when(accountTransactionRepository.findByAccountNumber(1L)).thenReturn(accountTransactionStream);
        Mockito.when(accountTransactionMapper.accountTransactionToAccountTransactionDTO(accountTransaction1)).thenReturn(accountTransactionDTO1);
        List<AccountTransactionDTO> accountTransactionDTOList = accountTransactionServiceImpl.getAccountTransactionsByAccountNumber(1L);
        AccountTransactionDTO actualAccountTransactionDTO = accountTransactionDTOList.stream().findAny().get();
        Assertions.assertEquals(accountTransactionDTO1.getTransactionType(), actualAccountTransactionDTO.getTransactionType());
        Assertions.assertEquals(accountTransactionDTO1.getCurrency(), actualAccountTransactionDTO.getCurrency());
        Assertions.assertEquals(accountTransactionDTO1.getCreditAmount(), actualAccountTransactionDTO.getCreditAmount());
        Assertions.assertEquals(accountTransactionDTO1.getValueDate(), actualAccountTransactionDTO.getValueDate());
        Assertions.assertEquals(accountTransactionDTO1.getTransactionNarrative(), actualAccountTransactionDTO.getTransactionNarrative());
    }

    @Test
    public void testGetAccountTransactionsByAccountNumberWhenAccountNumberNotPresent() throws AccountServiceException {
        Mockito.when(accountTransactionRepository.findByAccountNumber(1L)).thenReturn(Stream.empty());
        List<AccountTransactionDTO> accountTransactionDTOList = accountTransactionServiceImpl.getAccountTransactionsByAccountNumber(1L);
        Assertions.assertEquals(List.of().size(), accountTransactionDTOList.size());
    }

    @Test
    public void testGetAccountTransactionsByAccountNumberDuringDataAccessException() throws AccountServiceException {

        Mockito.when(accountTransactionRepository.findByAccountNumber(1L)).thenThrow(new DataAccessException(TestConstants.TESTING) {});
        AccountServiceException exception = Assertions.assertThrows(AccountServiceException.class,
                () -> accountTransactionServiceImpl.getAccountTransactionsByAccountNumber(1L));;
        Assertions.assertEquals(exception.getMessage(), TestConstants.TESTING);
    }
}
