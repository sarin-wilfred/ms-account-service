package org.anz.account.dto;

import lombok.*;
import org.anz.account.common.CurrencyType;
import org.anz.account.common.TransactionType;

import java.math.BigDecimal;
import java.sql.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransactionDTO {

    private Long accountNumber;

    private String accountName;

    private Date valueDate;

    private CurrencyType currency;

    private BigDecimal debitAmount;

    private BigDecimal creditAmount;

    private TransactionType transactionType;

    private String transactionNarrative;

}
