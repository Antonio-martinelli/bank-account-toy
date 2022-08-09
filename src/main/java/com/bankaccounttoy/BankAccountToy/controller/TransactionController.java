package com.bankaccounttoy.BankAccountToy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

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

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
public class TransactionController {
	
	@Autowired
	private final TransactionService transactionService;
	private Counter transactionsAmount;
	//private Logger logger = LoggerFactory.getLogger(TransactionController.class);
	private org.apache.logging.log4j.Logger log = LogManager.getLogger("ASYNC_JSON_FILE_APPENDER");
	
	TransactionController(TransactionService transactionService, MeterRegistry meterRegistry) {
		this.transactionService = transactionService;
		this.transactionsAmount = meterRegistry.counter("transactions_counter");
	}
	
	@PutMapping("/deposit")
	public ResponseEntity<BaseResponse> deposit(@RequestBody DepositForm depositForm) {
		try {
			//logger.info("Deposit : " + depositForm.toString());
			log.info("Second log : " + depositForm.toString());
			log.debug("Second log debug : " + depositForm.toString());
			log.warn("Second log warn");
			log.error("Second log error : " + depositForm.toString());
			ResponseEntity<BaseResponse> response = new ResponseEntity<BaseResponse>(
				transactionService.deposit(depositForm), HttpStatus.OK);
			
			transactionsAmount.increment(depositForm.getAmount());
			
			return response;
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
			//logger.info("Transaction : " + transactionForm.toString());
			ResponseEntity<BaseResponse> response = new ResponseEntity<BaseResponse>(
				transactionService.transferMoney(transactionForm), HttpStatus.OK);
			
			transactionsAmount.increment(transactionForm.getAmount());
			
			return response;
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
