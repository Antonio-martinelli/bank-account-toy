package com.bankaccounttoy.BankAccountToy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bankaccounttoy.BankAccountToy.exception.AccountNotFoundCustomException;
import com.bankaccounttoy.BankAccountToy.exception.InvalidRequestException;
import com.bankaccounttoy.BankAccountToy.form.AccountForm;
import com.bankaccounttoy.BankAccountToy.form.AccountLockForm;
import com.bankaccounttoy.BankAccountToy.models.Account;
import com.bankaccounttoy.BankAccountToy.repository.AccountRepository;
import com.bankaccounttoy.BankAccountToy.response.AccountBalanceResponse;
import com.bankaccounttoy.BankAccountToy.response.AccountHistoryResponse;
import com.bankaccounttoy.BankAccountToy.response.AccountLockedResponse;
import com.bankaccounttoy.BankAccountToy.response.AccountsListResponse;

@Component
public class AccountService {
	
	private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
    public AccountBalanceResponse getAccountBalance(AccountForm accountDetailsForm) {
    	String IBAN = accountDetailsForm.getIBAN();
    	if (IBAN == null) 
    		throw new InvalidRequestException();
    	Account account = accountRepository.findById(IBAN).orElseThrow(()->
        	new AccountNotFoundCustomException(IBAN));
    	return new AccountBalanceResponse(IBAN, account.getBalance());
    }
    
    public AccountHistoryResponse getAccountHistory(AccountForm accountHistoryForm) {
		String IBAN = accountHistoryForm.getIBAN();
		if (IBAN == null) 
    		throw new InvalidRequestException();
		Account account = accountRepository.findById(IBAN).orElseThrow(()->
        	new AccountNotFoundCustomException(IBAN));
		return new AccountHistoryResponse(IBAN, account.getTransactions());
	}
    
    public AccountsListResponse filterByType(String type) {
		List<Account> accountsListFiltered = new ArrayList<>();
		List<Account> accountsList = accountRepository.findAll();
		
		for (Account account : accountsList) {
		      if (account.getType().equals(type))
		    	  accountsListFiltered.add(account);
		}
		if (accountsListFiltered.size() == 0)
			throw new InvalidRequestException();
		return new AccountsListResponse(accountsListFiltered);
	}

	public AccountLockedResponse lockAccount(AccountLockForm accountLockForm) {
		String IBAN = accountLockForm.getIBAN();
		Boolean lock = accountLockForm.isLock();
		
    	if (IBAN == null) 
    		throw new InvalidRequestException();
    	if (lock == null)
    		throw new InvalidRequestException();
    	Account account = accountRepository.findById(IBAN).orElseThrow(()->
        	new AccountNotFoundCustomException(IBAN));
    	account.setLocked(lock);
    	accountRepository.save(account);
    	return new AccountLockedResponse(lock);
	}
	
}
