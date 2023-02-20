package org.anz.account.integration;

import org.anz.account.common.AccountType;
import org.anz.account.common.CurrencyType;
import org.anz.account.common.TransactionType;
import org.anz.account.controller.AccountController;
import org.anz.account.controller.AccountTransactionController;
import org.anz.account.dto.AccountDTO;
import org.anz.account.dto.AccountTransactionDTO;
import org.anz.account.exception.AccountServiceException;
import org.anz.account.repository.AccountRepository;
import org.anz.account.repository.AccountTransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootTest
public class ApiIntegrationTest {

    @Autowired
    private AccountController accountController;

    @Autowired
    private AccountTransactionController accountTransactionController;

    @Autowired
    private WebTestClient webTestClient;


    @Test
    public void injectedComponentsAreNotNull() {
        Assertions.assertNotNull(accountController);
        Assertions.assertNotNull(accountTransactionController);
    }

    @Test
    public void testGetAccountsByUserProfileIdForSuccess() throws AccountServiceException {
        AccountDTO accountDTO = accountController.getAccountsByUserProfileId(1L).getBody().stream().findFirst().get();
        Assertions.assertEquals(1, accountDTO.getAccountNumber());
        Assertions.assertEquals("TestX11", accountDTO.getAccountName());
        Assertions.assertEquals(AccountType.SAVINGS, accountDTO.getAccountType());
        Assertions.assertEquals(CurrencyType.AUD, accountDTO.getCurrency());
        Assertions.assertEquals(new BigDecimal(1000), accountDTO.getOpeningAvailableBalance());
    }

    @Test
    public void testGetAccountTransactionsByAccountNumberForSuccess() throws AccountServiceException {
        AccountTransactionDTO accountTransactionDTO = accountTransactionController.getAccountTransactionsByAccountNumber(1L).getBody().stream().findFirst().get();
        Assertions.assertEquals(1, accountTransactionDTO.getAccountNumber());
        Assertions.assertEquals("TestX11", accountTransactionDTO.getAccountName());
        Assertions.assertEquals(CurrencyType.AUD, accountTransactionDTO.getCurrency());
        Assertions.assertEquals(new BigDecimal(300), accountTransactionDTO.getCreditAmount());
        Assertions.assertEquals(TransactionType.CREDIT, accountTransactionDTO.getTransactionType());
    }

    @Test
    public void testApiAccountsForSuccess() throws AccountServiceException {
        webTestClient
                .post()
                .uri("/api/accounts/{userProfileId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody();

    }

    @Test
    public void testApiAccountTransactionsForSuccess() throws AccountServiceException {
        webTestClient
                .post()
                .uri("/api/accountTransactions/{accountNumber}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody();

    }

    @Test
    public void testApiAccountsForNumberFormatException() throws AccountServiceException {
        webTestClient
                .post()
                .uri("/api/accounts/{userProfileId}", "1a")
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody();

    }

    @Test
    public void testApiAccountTransactionsForNumberFormatException() throws AccountServiceException {
        webTestClient
                .post()
                .uri("/api/accountTransactions/{accountNumber}", "1a")
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody();

    }


}
