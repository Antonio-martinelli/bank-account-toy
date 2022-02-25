package com.bankaccounttoy.BankAccountToy.exception;

public class InvalidTransactionException extends RuntimeException {

	  public InvalidTransactionException() {
	    super("Transaction failed");
	  }
	}