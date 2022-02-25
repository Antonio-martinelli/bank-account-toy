package com.bankaccounttoy.BankAccountToy.form;

import javax.validation.constraints.Pattern;

public class AccountLockForm {

	private String IBAN;
	@Pattern(regexp = "^(true|false)$")
	private Boolean lock;
	
	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String IBAN) {
		this.IBAN = IBAN;
	}

	public Boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}
}
