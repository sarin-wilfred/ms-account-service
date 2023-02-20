package org.anz.account.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.anz.account.dto.AccountTransactionDTO;
import org.anz.account.exception.AccountServiceException;
import org.anz.account.mapper.AccountTransactionMapper;
import org.anz.account.model.AccountTransaction;
import org.anz.account.repository.AccountTransactionRepository;
import org.anz.account.service.AccountTransactionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
@RequiredArgsConstructor
@Service
public class AccountTransactionServiceImpl implements AccountTransactionService {

    private final AccountTransactionRepository accountTransactionRepository;
    private final AccountTransactionMapper accountTransactionMapper;


    /**
     * This method is used to list all account transactions by accountNumber
     *@Transactional is used as we are operating on Streams
     *
     * @param accountNumber
     * @return List<AccountTransactionDTO>
     */
    @Transactional
    public List<AccountTransactionDTO> getAccountTransactionsByAccountNumber(Long accountNumber) throws AccountServiceException {
        try(Stream<AccountTransaction> accountTransactionStream = accountTransactionRepository.findByAccountNumber(accountNumber);) {
            return accountTransactionStream
                    .map(accountTransaction -> accountTransactionMapper.accountTransactionToAccountTransactionDTO(accountTransaction))
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            throw new AccountServiceException(exception.getMessage());
        }
    }
}
