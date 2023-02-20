package org.anz.account.dto;

import lombok.*;
import org.anz.account.common.AccountType;
import org.anz.account.common.CurrencyType;
import org.anz.account.model.UserProfile;

import java.math.BigDecimal;
import java.sql.Date;


@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Long accountNumber;

    private String accountName;

    private AccountType accountType;

    private Date balanceDate;

    private CurrencyType currency;

    private BigDecimal openingAvailableBalance;

}
