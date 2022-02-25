package com.bankaccounttoy.BankAccountToy.response;

import java.util.List;

import com.bankaccounttoy.BankAccountToy.models.Account;

public class AccountsListResponse extends BaseResponse {
	
	public AccountsListResponse() {};

	public AccountsListResponse(List<Account> accounts) {
		super();
		this.accounts = accounts;
	}

	private List<Account> accounts;

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
}
