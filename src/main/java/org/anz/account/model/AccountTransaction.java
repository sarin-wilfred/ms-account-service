package org.anz.account.model;

import jakarta.persistence.*;
import lombok.*;
import org.anz.account.common.CurrencyType;
import org.anz.account.common.TransactionType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccountTransaction {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_number", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;

    @Column(nullable = false, unique = false)
    private Date valueDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false, unique = false)
    private CurrencyType currency;

    @Column(nullable = true, unique = false)
    private BigDecimal debitAmount;

    @Column(nullable = true, unique = false)
    private BigDecimal creditAmount;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false, unique = false)
    private TransactionType transactionType;

    @Column(length = 100, nullable = true, unique = false)
    private String transactionNarrative;

    @Version
    private Integer version;

    @CreatedDate
    private Timestamp createdDate;

    @LastModifiedDate
    private Timestamp lastModifiedDate;
}
