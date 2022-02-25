package com.bankaccounttoy.BankAccountToy.response;

import java.util.List;

import com.bankaccounttoy.BankAccountToy.models.Transaction;

public class TransactionHistoryResponse extends BaseResponse {

	private String IBAN;
	private List<Transaction> transactions;
	
	public String getIBAN() {
		return IBAN;
	}
	public void setIBAN(String IBAN) {
		this.IBAN = IBAN;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}
