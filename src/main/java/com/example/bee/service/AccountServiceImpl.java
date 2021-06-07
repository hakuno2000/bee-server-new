package com.example.bee.service;

import com.example.bee.entity.Account;
import com.example.bee.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public Account findAccountByPhone(String phone) {
        return accountRepo.findAccountByPhone(phone);
    }

    @Override
    public Account findAccountById(long id) {
        return accountRepo.findAccountById(id);
    }

    @Override
    public Account login(Account account) {
        Account matchedAccount = accountRepo.findAccountByPhone(account.getPhone());
        if (matchedAccount.getPassword().equals(account.getPassword())) return matchedAccount;
        else return null;
    }

    @Override
    public List<Account> findAll() {
        return accountRepo.findAll();
    }

    @Override
    public List<Account> getShipper(Integer type) {
        return accountRepo.findAccountByType(type);
    }

    @Override
    public void save(Account account) {
        accountRepo.save(account);
    }


}
