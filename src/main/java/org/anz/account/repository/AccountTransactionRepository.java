package org.anz.account.repository;

import org.anz.account.model.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {

    public Stream<AccountTransaction> findByAccountNumber(Long accountNumber);

}
