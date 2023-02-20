package org.anz.account.integration;

import org.anz.account.repository.AccountRepository;
import org.anz.account.repository.AccountTransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RepositoryIntegrationTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        Assertions.assertNotNull(accountRepository);
        Assertions.assertNotNull(accountTransactionRepository);
    }

    @Test
    public void testFindByUserProfileIdForSuccess() {
        Assertions.assertEquals(3, accountRepository.findByuserProfileId(1L).count());
    }

    @Test
    public void testFindByAccountNumberForSuccess() {
        Assertions.assertEquals(3, accountTransactionRepository.findByAccountNumber(1L).count());
    }

    @Test
    public void testFindByUserProfileIdForFailure() {
        Assertions.assertEquals(0, accountRepository.findByuserProfileId(-1L).count());
    }

}
