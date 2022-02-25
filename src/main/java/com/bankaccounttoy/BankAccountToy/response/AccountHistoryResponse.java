package com.bankaccounttoy.BankAccountToy.response;

import java.util.List;

import com.bankaccounttoy.BankAccountToy.models.Transaction;

public class AccountHistoryResponse extends BaseResponse {

	private String IBAN;
	private List<Transaction> history;

	public AccountHistoryResponse(String IBAN, List<Transaction> history) {
		this.IBAN = IBAN;
		this.history = history;
	}
	
	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String IBAN) {
		this.IBAN = IBAN;
	}
	
	public List<Transaction> getHistory() {
		return history;
	}

	public void setHistory(List<Transaction> history) {
		this.history = history;
	}
	
}
