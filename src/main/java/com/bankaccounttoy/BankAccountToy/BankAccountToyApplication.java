package com.bankaccounttoy.BankAccountToy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@SpringBootApplication
public class BankAccountToyApplication {
  
    public static void main(String[] args) {
    	SpringApplication.run(BankAccountToyApplication.class, args);
    }
    
}
