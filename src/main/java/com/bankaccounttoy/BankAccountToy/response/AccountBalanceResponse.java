package com.bankaccounttoy.BankAccountToy.response;

public class AccountBalanceResponse extends BaseResponse {
	
	private String IBAN;
	private double amount;
	
	public AccountBalanceResponse(String IBAN, double amount) {
		this.IBAN = IBAN;
		this.amount = amount;
	}
	
	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
