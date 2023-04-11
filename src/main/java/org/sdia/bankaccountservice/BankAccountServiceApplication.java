package org.sdia.bankaccountservice;

import org.sdia.bankaccountservice.entities.BankAccount;
import org.sdia.bankaccountservice.entities.Customer;
import org.sdia.bankaccountservice.enums.AccountType;
import org.sdia.bankaccountservice.repositories.BankAccountRepository;
import org.sdia.bankaccountservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository) {
		return args -> {

			Stream.of("ali","anas","amine").forEach(c->{
				Customer customer= Customer.builder()
						.name(c)
						.build();
				customerRepository.save(customer);
				customerRepository.findAll().forEach(cs->{
					for (int i = 0; i < 4; i++) {
						BankAccount bankAccount = BankAccount.builder()
								.id(UUID.randomUUID().toString())
								.type(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
								.balance(10000 + Math.random() * 90000)
								.createdAt(new Date())
								.currency("MAD")
								.customer(cs)
								.build();
						bankAccountRepository.save(bankAccount);
					}
				});

			});


		};
	}
}
