package com.bankaccounttoy.BankAccountToy.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.bankaccounttoy.BankAccountToy.models.Account;

@Transactional
public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findById(@Param("IBAN")String IBAN);   
    
}