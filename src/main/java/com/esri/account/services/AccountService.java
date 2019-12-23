package com.esri.account.services;

import com.esri.account.bean.Account;
import com.esri.account.exceptions.AccountException;
import com.esri.account.repository.AccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Iterable<Account> findAllAccountData(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    public Iterable<Account> findAccountDataByAccountNo(Pageable pageable,Long accountNo) {
        return accountRepository.findByAccountNo(pageable,accountNo);
    }

    public Account addNewAccountData(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccountData(Long accountNo) {
        try{
            accountRepository.deleteById(accountNo);
        }catch (Exception e){
            throw new AccountException("ID does not exists");
        }
    }
}
