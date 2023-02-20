package org.anz.account.service;

import org.anz.account.dto.AccountDTO;
import org.anz.account.exception.AccountServiceException;

import java.util.List;

public interface AccountService {

    public List<AccountDTO> getAccountsByUserProfileId(Long userProfileId) throws AccountServiceException;

}
