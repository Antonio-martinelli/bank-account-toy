package com.bankaccounttoy.BankAccountToy.response;

public class AccountLockedResponse extends BaseResponse {

	private String resultDescription = "Lock for the account is now set to ";
	
	public AccountLockedResponse(boolean lock) {
		this.resultDescription += lock;
	}
	
	public String getResultDescription() {
		return resultDescription;
	}
	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}
	
}
