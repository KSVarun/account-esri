package com.esri.account;

import com.esri.account.bean.Account;
import com.esri.account.bean.Api;
import com.esri.account.repository.APIRepository;
import com.esri.account.repository.AccountRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootApplication
public class AccountApplication implements CommandLineRunner {
	@Value("classpath:transaction.json")
	private Resource transactionResource;

	@Value("classpath:api.json")
	private Resource apiResource;

	private ObjectMapper objectMapper;
	private AccountRepository accountRepository;
	private APIRepository apiRepository;

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

	public AccountApplication( AccountRepository accountRepository, APIRepository apiRepository) {
		this.objectMapper = new ObjectMapper();
		this.accountRepository = accountRepository;
		this.apiRepository = apiRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		try {
			byte[] accountData = FileCopyUtils.copyToByteArray(transactionResource.getInputStream());
			String accountDataItems = new String(accountData, StandardCharsets.UTF_8);
			List<Account> accountsData = objectMapper.readValue(accountDataItems, new TypeReference<List<Account>>() {
			});

			byte[] apiData = FileCopyUtils.copyToByteArray(apiResource.getInputStream());
			String apiItems = new String(apiData, StandardCharsets.UTF_8);
			List<Api> apis = objectMapper.readValue(apiItems, new TypeReference<List<Api>>() {
			});

			accountRepository.saveAll(accountsData);
			apiRepository.saveAll(apis);

		} catch (Exception se) {
			se.printStackTrace();
		}
	}
}
