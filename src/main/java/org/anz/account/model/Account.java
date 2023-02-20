package org.anz.account.model;

import jakarta.persistence.*;
import lombok.*;
import org.anz.account.common.AccountType;
import org.anz.account.common.CurrencyType;
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
public class Account {

    @Id
    private Long number;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;

    @Column(length = 50, nullable = false, unique = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false, unique = false)
    private AccountType type;

    @Column(nullable = false, unique = false)
    private Date balanceDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false, unique = false)
    private CurrencyType currency;

    @Column(nullable = false, unique = false)
    private BigDecimal openingAvailableBalance;

    @Version
    private Integer version;

    @CreatedDate
    private Timestamp createdDate;

    @LastModifiedDate
    private Timestamp lastModifiedDate;

//    @OneToMany(mappedBy="account_transaction", orphanRemoval=true, cascade={CascadeType.ALL})
//    private List<AccountTransaction> accountTransactions;
}
