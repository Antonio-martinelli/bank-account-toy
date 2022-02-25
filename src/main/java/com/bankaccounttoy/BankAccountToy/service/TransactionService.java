package com.bankaccounttoy.BankAccountToy.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bankaccounttoy.BankAccountToy.exception.AccountNotFoundCustomException;
import com.bankaccounttoy.BankAccountToy.exception.InvalidAmountException;
import com.bankaccounttoy.BankAccountToy.exception.InvalidRequestException;
import com.bankaccounttoy.BankAccountToy.exception.InvalidTransactionException;
import com.bankaccounttoy.BankAccountToy.form.DepositForm;
import com.bankaccounttoy.BankAccountToy.form.TransactionForm;
import com.bankaccounttoy.BankAccountToy.models.Account;
import com.bankaccounttoy.BankAccountToy.models.Transaction;
import com.bankaccounttoy.BankAccountToy.repository.AccountRepository;
import com.bankaccounttoy.BankAccountToy.repository.TransactionRepository;
import com.bankaccounttoy.BankAccountToy.response.BaseResponse;

@Component
public class TransactionService {
	
	private final TransactionRepository transactionRepository;
	private final AccountRepository accountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
    		AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }
    
    public BaseResponse deposit(DepositForm depositForm) {
    	String IBAN = depositForm.getIBAN();
    	double amount = depositForm.getAmount();
    	
    	if (IBAN == null) 
    		throw new InvalidRequestException();
    	if (amount <= 0)
    		throw new InvalidAmountException();
    	
    	Account account = accountRepository.findById(IBAN)
    			.orElseThrow(()-> new AccountNotFoundCustomException(IBAN));
    	
    	LocalDateTime transactionTime = LocalDateTime.now();
    	Transaction outTransaction = new Transaction(
    			transactionTime, IBAN, "Deposit", amount);
    	transactionRepository.save(outTransaction);
    	
		account.setBalance(account.getBalance() + amount);
		accountRepository.save(account);
		BaseResponse response = new BaseResponse();
    	
    	return response;
	}
    
    public BaseResponse transferMoney(TransactionForm transactionForm) {
    	String sender = transactionForm.getSender();
    	String receiver = transactionForm.getReceiver();
    	double amount = transactionForm.getAmount();
    	
    	if (sender == null) 
    		throw new InvalidRequestException();
    	if (receiver == null)
    		throw new InvalidRequestException();
    	if (amount <= 0)
    		throw new InvalidAmountException();
    	
    	Account senderAccount = accountRepository.findById(sender)
    			.orElseThrow(()-> new AccountNotFoundCustomException(sender));
    	
    	if (senderAccount.isLocked())
    		throw new InvalidTransactionException();
    	
    	double senderBalance = senderAccount.getBalance();
    	
    	if (senderBalance < amount)
    		throw new InvalidTransactionException();
    	
    	if (senderAccount.getType().equals("SavingsAccount") && 
    			!senderAccount.getReferenceAccount().equals(receiver))
    		throw new InvalidTransactionException();
    	
    	if (senderAccount.getType().equals("PrivateLoanAccount"))
    		throw new InvalidTransactionException();
    	
    	Account receiverAccount = accountRepository.findById(receiver)
    			.orElseThrow(()-> new AccountNotFoundCustomException(receiver));
    	
    	senderAccount.setBalance(senderBalance - amount);
    	receiverAccount.setBalance(receiverAccount.getBalance() + amount);
    	
    	LocalDateTime transactionTime = LocalDateTime.now();
    	Transaction outTransaction = new Transaction(
    			transactionTime, sender, "OUT", amount);
    	transactionRepository.save(outTransaction);
    	
    	Transaction inTransaction = new Transaction(
    			transactionTime, receiver, "IN", amount);
    	transactionRepository.save(inTransaction);
    	
    	accountRepository.save(senderAccount);
    	accountRepository.save(receiverAccount);
 
    	return new BaseResponse();
    	
    }
    
}
