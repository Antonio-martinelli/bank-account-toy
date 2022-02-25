package com.bankaccounttoy.BankAccountToy.exception;

public class InvalidRequestException extends RuntimeException {

	  public InvalidRequestException() {
	    super("Invalid request.");
	  }
	}