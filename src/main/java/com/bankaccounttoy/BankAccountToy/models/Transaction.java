package com.bankaccounttoy.BankAccountToy.models;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Transaction {
	
	@Id 
	@GeneratedValue
	private Long id;
	private LocalDateTime time;
	private String account;
	private String transactionDirection;
	private double amount;
	
	Transaction() {};
	
	public Transaction(LocalDateTime time, String account, String transactionDirection, double amount) {
		this.time = time;
		this.account = account;
		this.transactionDirection = transactionDirection;
		this.amount = amount;
	}
	
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getTransactionDirection() {
		return transactionDirection;
	}

	public void setTransactionDirection(String transactionDirection) {
		this.transactionDirection = transactionDirection;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public String toString() {
		return "Transaction [id=" + id + 
				", time=" + time + 
				", account=" + account + 
				", transactionDirection=" + transactionDirection + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Objects.equals(id, other.id);
	}
	
}
