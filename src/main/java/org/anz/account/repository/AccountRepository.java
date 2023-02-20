package org.anz.account.repository;

import org.anz.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    public Stream<Account> findByuserProfileId(Long userProfileId);

}
