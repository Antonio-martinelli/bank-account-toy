package com.bankaccounttoy.BankAccountToy.form;

public class AccountForm {

	private String IBAN;
	
	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String IBAN) {
		this.IBAN = IBAN;
	}
	
	@Override
	public String toString() {
		return "IBAN=" + IBAN + "]";
	}
}
