package org.anz.account.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.anz.account.dto.AccountDTO;
import org.anz.account.exception.AccountServiceException;
import org.anz.account.mapper.AccountMapper;
import org.anz.account.model.Account;
import org.anz.account.repository.AccountRepository;
import org.anz.account.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    /**
     * This method is used to list all accounts by user profile id
     * @Transactional is used as we are operating on Streams
     *
     * @param userProfileId
     * @return List<Account>
     */
    @Transactional
    public List<AccountDTO> getAccountsByUserProfileId(Long userProfileId) throws AccountServiceException {
        try(Stream<Account> accountStream = accountRepository.findByuserProfileId(userProfileId)) {
            return accountStream
                    .map(account -> accountMapper.accountToAccountDTO(account))
                    .collect(Collectors.toList());
        } catch(Exception exception) {
            throw new AccountServiceException(exception.getMessage());
        }
    }

}
