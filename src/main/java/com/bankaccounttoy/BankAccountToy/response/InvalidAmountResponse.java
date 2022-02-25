package com.bankaccounttoy.BankAccountToy.response;

public class InvalidAmountResponse extends BaseResponse {

	private String resultDescription = "Specified amount is not valid.";
	
	public String getResultDescription() {
		return resultDescription;
	}
	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}
	
}
