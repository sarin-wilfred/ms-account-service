package org.anz.account.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;

@Data
@Entity
public class UserProfile {

    @Id
    private Long id;

    @Column(length = 50, nullable = false, unique = false)
    private String userName;

    @Version
    private Integer version;

    @CreatedDate
    private Timestamp createdDate;

    @LastModifiedDate
    private Timestamp lastModifiedDate;

//    @OneToMany(mappedBy="account", orphanRemoval=true, cascade={CascadeType.ALL})
//    private List<Account> accounts;

//    @OneToMany(mappedBy="account_transaction", orphanRemoval=true, cascade={CascadeType.ALL})
//    private List<AccountTransaction> accountTransactions;
}
