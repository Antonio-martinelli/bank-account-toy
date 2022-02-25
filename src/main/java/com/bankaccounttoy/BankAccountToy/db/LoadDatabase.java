package com.bankaccounttoy.BankAccountToy.db;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bankaccounttoy.BankAccountToy.models.Account;
import com.bankaccounttoy.BankAccountToy.models.Transaction;
import com.bankaccounttoy.BankAccountToy.repository.AccountRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(AccountRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Account("DE34857273857271800213", 
				"Alexander", "Smith", "alexander.smith@gmail.com", 
				"CheckingAccount", false, new ArrayList<Transaction>())));
      log.info("Preloading " + repository.save(new Account("DE34854002857200800234", 
				"Igor", "Doe", "igor.doe@gmail.com", 
				"CheckingAccount", false, new ArrayList<Transaction>())));
      log.info("Preloading " + repository.save(new Account("DE34857273800271880411", 
				"Antonio", "Martinelli", "antonio.martinelli@gmail.com", 
				"CheckingAccount", false, new ArrayList<Transaction>())));
      log.info("Preloading " + repository.save(new Account("DE34857243850271880415", 
				"Victoria", "Schwartz", "victoria.schwartz@gmail.com", 
				"CheckingAccount", false, new ArrayList<Transaction>())));
      log.info("Preloading " + repository.save(new Account("DE34857274830271880419", 
				"Victoria", "Schwartz", "victoria.schwartz@gmail.com", 
				"SavingsAccount", false, "DE34857243850271880415", new ArrayList<Transaction>())));
      log.info("Preloading " + repository.save(new Account("DE34857274830274850617", 
				"Antonio", "Martinelli", "antonio.martinelli@gmail.com", 
				"PrivateLoanAccount", false, new ArrayList<Transaction>())));
    };
  }
}