package com.bankaccounttoy.BankAccountToy.response;

public class InvalidRequestResponse extends BaseResponse {

	private String resultDescription = "Invalid request.";
	
	public String getResultDescription() {
		return resultDescription;
	}
	
	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}
	
}
