package org.anz.account.mapper;

import org.anz.account.dto.AccountDTO;
import org.anz.account.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target="accountNumber", source="entity.number")
    @Mapping(target="accountName", source="entity.name")
    @Mapping(target="accountType", source="entity.type")
    AccountDTO accountToAccountDTO(Account entity);
}
