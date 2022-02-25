package com.bankaccounttoy.BankAccountToy.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
  

@Entity
public class Account {
	
	@Id
	@Pattern(regexp = "^DE\\d{20}$")	//A very simple regex specific for German IBANs
	private String IBAN;
	private String name;
	private String surname;
	private String email;
	private String type;
	private boolean isLocked;
	private double balance;
	private String referenceAccount;
	@OneToMany(mappedBy="account")
	private List<Transaction> transactions;
	
	Account() {}
	
	public Account(String IBAN, String name, String surname, String email, 
			String type, boolean isLocked, List<Transaction> transactions) {
		this.IBAN = IBAN;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.type = type;
		this.isLocked = isLocked;
		this.balance = 0;
		this.transactions = transactions;
	}
	
	//Different constructor to specify a referenceAccount
	public Account(String IBAN, String name, String surname, String email, 
			String type, boolean isLocked, String referenceAccount, List<Transaction> transactions) {
		this.IBAN = IBAN;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.type = type;
		this.isLocked = isLocked;
		this.balance = 0;
		this.referenceAccount = referenceAccount;
		this.transactions = transactions;
	}
	
	public String getIBAN() {
		return IBAN;
	}
	
	public void setIBAN(String IBAN) {
		this.IBAN = IBAN;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getType() {
		return type;
	}
 	
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getReferenceAccount() {
		return referenceAccount;
	}

	public void setReferenceAccount(String referenceAccount) {
		this.referenceAccount = referenceAccount;
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(IBAN, email, name, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(IBAN, other.IBAN) && Objects.equals(email, other.email)
				&& Objects.equals(name, other.name) && Objects.equals(surname, other.surname);
	}
	
	@Override
	public String toString() {
		return "Account{" +
				"IBAN='" + IBAN + '\'' +
				"name='" + name + '\'' +
				"surname='" + surname + '\'' +
				"email='" + email + '\'' +
				"type='" + type + '\'' +
				"isLocked='" + isLocked + '\'' +
				"balance='" + balance + '\'' +
				'}';
	  }

}


