package com.bankaccounttoy.BankAccountToy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.bankaccounttoy.BankAccountToy.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

    public Transaction findById(@Param("Id")long Id);
    
    public Transaction findByAccount(@Param("account") String account);
    
}