package com.bankaccounttoy.BankAccountToy.exception;

public class AccountNotFoundCustomException extends RuntimeException {

	  public AccountNotFoundCustomException(String IBAN) {
	    super("Could not find account " + IBAN);
	  }
	}