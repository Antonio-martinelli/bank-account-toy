package com.bankaccounttoy.BankAccountToy.controller;

import com.bankaccounttoy.BankAccountToy.response.AccountNotFoundResponse;
import com.bankaccounttoy.BankAccountToy.response.BaseResponse;
import com.bankaccounttoy.BankAccountToy.response.InvalidRequestResponse;
import com.bankaccounttoy.BankAccountToy.service.AccountService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankaccounttoy.BankAccountToy.exception.AccountNotFoundCustomException;
import com.bankaccounttoy.BankAccountToy.exception.InvalidRequestException;
import com.bankaccounttoy.BankAccountToy.form.AccountForm;
import com.bankaccounttoy.BankAccountToy.form.AccountLockForm;

@RestController
public class AccountController {

	@Autowired
	private final AccountService accountService;
	private Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@PostMapping("/accounts/balance")
	ResponseEntity<BaseResponse> getAccountBalance(@RequestBody AccountForm accountDetailsForm) {
		try {
			logger.info("Balance : " + accountDetailsForm.toString());
			return new ResponseEntity<BaseResponse>(
					accountService.getAccountBalance(accountDetailsForm), HttpStatus.OK);
		} catch (AccountNotFoundCustomException e) {
			return new ResponseEntity<BaseResponse>(
					new AccountNotFoundResponse(), HttpStatus.NOT_FOUND);
		} catch (InvalidRequestException e) {
			return new ResponseEntity<BaseResponse>(
					new InvalidRequestResponse(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/accountsByType/{type}")
	ResponseEntity<BaseResponse> filterByType(@PathVariable String type) {
		try {
			logger.info("AccountsByType : " + type);
			return new ResponseEntity<BaseResponse>(
					accountService.filterByType(type), HttpStatus.OK);
		} catch (InvalidRequestException e) {
			return new ResponseEntity<BaseResponse>(
					new InvalidRequestResponse(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("accounts/history")
	public ResponseEntity<BaseResponse> getAccountHistory(@RequestBody AccountForm accountForm) {
		try {
			logger.info("Account history : " + accountForm.toString());
			return new ResponseEntity<BaseResponse>(
					accountService.getAccountHistory(accountForm), HttpStatus.OK);
		} catch (AccountNotFoundCustomException e) {
			return new ResponseEntity<BaseResponse>(
					new AccountNotFoundResponse(), HttpStatus.NOT_FOUND);
		} catch (InvalidRequestException e) {
			return new ResponseEntity<BaseResponse>(
					new InvalidRequestResponse(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/lockAccount")
	public ResponseEntity<BaseResponse> lockAccount(@RequestBody AccountLockForm accountLockForm) {
		try {
			logger.info("Lock account : " + accountLockForm.toString());
			return new ResponseEntity<BaseResponse>(
					accountService.lockAccount(accountLockForm), HttpStatus.OK);
		} catch (AccountNotFoundCustomException e) {
			return new ResponseEntity<BaseResponse>(
					new AccountNotFoundResponse(), HttpStatus.NOT_FOUND);
		}
	}
	
}
