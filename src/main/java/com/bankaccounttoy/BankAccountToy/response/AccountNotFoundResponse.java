package com.bankaccounttoy.BankAccountToy.response;

public class AccountNotFoundResponse extends BaseResponse {

	private String resultDescription = "Account not found";
	
	public String getResultDescription() {
		return resultDescription;
	}
	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}
	
}
