package org.anz.account.mapper;

import org.anz.account.dto.AccountDTO;
import org.anz.account.dto.AccountTransactionDTO;
import org.anz.account.model.Account;
import org.anz.account.model.AccountTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountTransactionMapper {

//    @Mapping(target="employeeId", source="entity.id")
    @Mapping(target="accountNumber", source="entity.account.number")
    @Mapping(target="accountName", source="entity.account.name")
    AccountTransactionDTO accountTransactionToAccountTransactionDTO(AccountTransaction entity);
}
