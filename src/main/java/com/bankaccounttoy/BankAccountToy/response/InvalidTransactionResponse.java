package com.bankaccounttoy.BankAccountToy.response;

public class InvalidTransactionResponse extends BaseResponse {

	private String resultDescription;

	public InvalidTransactionResponse(String resultDescription) {
		this.resultDescription = resultDescription;
	}
	
	public String getResultDescription() {
		return resultDescription;
	}
	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}
	
}
