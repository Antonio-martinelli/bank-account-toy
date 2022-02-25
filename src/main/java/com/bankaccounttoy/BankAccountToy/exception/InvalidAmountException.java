package com.bankaccounttoy.BankAccountToy.exception;

public class InvalidAmountException extends RuntimeException {

	  public InvalidAmountException() {
	    super("Invalid amount.");
	  }
	}