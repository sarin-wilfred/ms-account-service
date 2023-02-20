package org.anz.account.helper;

import org.anz.account.common.AccountType;
import org.anz.account.common.CurrencyType;
import org.anz.account.common.TestConstants;
import org.anz.account.common.TransactionType;
import org.anz.account.dto.AccountDTO;
import org.anz.account.dto.AccountTransactionDTO;
import org.anz.account.model.Account;
import org.anz.account.model.AccountTransaction;

import java.math.BigDecimal;
import java.sql.Date;

public final class TestHelper {

    public static Long TIME_MILLIS = System.currentTimeMillis();

    public static Account getAccount1() {
        return Account.builder()
                .number(1L)
                .name("Test1")
                .type(AccountType.SAVINGS)
                .balanceDate(new Date(TIME_MILLIS))
                .currency(CurrencyType.AUD)
                .openingAvailableBalance(new BigDecimal(1000))
                .build();
    }

    public static AccountDTO getAccountDTO1() {
        return AccountDTO.builder()
                .accountNumber(1L)
                .accountName("Test1")
                .accountType(AccountType.SAVINGS)
                .balanceDate(new Date(TIME_MILLIS))
                .currency(CurrencyType.AUD)
                .openingAvailableBalance(new BigDecimal(1000))
                .build();
    }

    public static AccountTransaction getAccountTransaction1() {
        return AccountTransaction.builder()
                .valueDate(new Date(TIME_MILLIS))
                .currency(CurrencyType.AUD)
                .creditAmount(new BigDecimal(2000))
                .transactionType(TransactionType.CREDIT)
                .transactionNarrative(TestConstants.TESTING)
                .build();
    }

    public static AccountTransactionDTO getAccountTransactionDTO1() {
        return AccountTransactionDTO.builder()
                .valueDate(new Date(TIME_MILLIS))
                .currency(CurrencyType.AUD)
                .creditAmount(new BigDecimal(2000))
                .transactionType(TransactionType.CREDIT)
                .transactionNarrative(TestConstants.TESTING)
                .build();
    }

}
