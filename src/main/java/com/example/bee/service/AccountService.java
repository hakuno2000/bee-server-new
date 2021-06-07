package com.example.bee.service;

import com.example.bee.entity.Account;

import java.util.List;

public interface AccountService {
    Account findAccountByPhone(String phone);
    Account findAccountById(long id);
    Account login(Account account);
    List<Account> findAll();
    List<Account> getShipper(Integer type);
    void save(Account account);
}
