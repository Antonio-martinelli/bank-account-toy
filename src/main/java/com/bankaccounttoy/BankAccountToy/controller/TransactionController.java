package com.bankaccounttoy.BankAccountToy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankaccounttoy.BankAccountToy.exception.AccountNotFoundCustomException;
import com.bankaccounttoy.BankAccountToy.exception.InvalidAmountException;
import com.bankaccounttoy.BankAccountToy.exception.InvalidRequestException;
import com.bankaccounttoy.BankAccountToy.exception.InvalidTransactionException;
import com.bankaccounttoy.BankAccountToy.form.DepositForm;
import com.bankaccounttoy.BankAccountToy.form.TransactionForm;
import com.bankaccounttoy.BankAccountToy.response.AccountNotFoundResponse;
import com.bankaccounttoy.BankAccountToy.response.BaseResponse;
import com.bankaccounttoy.BankAccountToy.response.InvalidAmountResponse;
import com.bankaccounttoy.BankAccountToy.response.InvalidRequestResponse;
import com.bankaccounttoy.BankAccountToy.response.InvalidTransactionResponse;
import com.bankaccounttoy.BankAccountToy.service.TransactionService;

@RestController
public class TransactionController {
	
	@Autowired
	private final TransactionService transactionService;
	
	TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	@PutMapping("/deposit")
	public ResponseEntity<BaseResponse> deposit(@RequestBody DepositForm depositForm) {
		try {
			return new ResponseEntity<BaseResponse>(
					transactionService.deposit(depositForm), HttpStatus.OK);
		} catch (InvalidAmountException e) {
			return new ResponseEntity<BaseResponse>(
					new InvalidAmountResponse(), HttpStatus.BAD_REQUEST);
		} catch (AccountNotFoundCustomException e) {
			return new ResponseEntity<BaseResponse>(
					new AccountNotFoundResponse(), HttpStatus.NOT_FOUND);
		} catch (InvalidRequestException e) {
			return new ResponseEntity<BaseResponse>(
					new InvalidRequestResponse(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/transactions")
	public ResponseEntity<BaseResponse> transfer(@RequestBody TransactionForm transactionForm) {
		try {
			return new ResponseEntity<BaseResponse>(
					transactionService.transferMoney(transactionForm), HttpStatus.OK);
		} catch (InvalidTransactionException e) {
			return new ResponseEntity<BaseResponse>(
					new InvalidTransactionResponse(e.getMessage()), HttpStatus.FORBIDDEN);
		} catch (InvalidAmountException e) {
			return new ResponseEntity<BaseResponse>(
					new InvalidAmountResponse(), HttpStatus.BAD_REQUEST);
		} catch (AccountNotFoundCustomException e) {
			return new ResponseEntity<BaseResponse>(
					new AccountNotFoundResponse(), HttpStatus.NOT_FOUND);
		} catch (InvalidRequestException e) {
			return new ResponseEntity<BaseResponse>(
					new InvalidRequestResponse(), HttpStatus.BAD_REQUEST);
		}
	}
	
}
