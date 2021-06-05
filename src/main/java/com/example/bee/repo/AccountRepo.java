package com.example.bee.repo;

import com.example.bee.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    Account findAccountByPhone(String phone);
    Account findAccountById(long id);
    List<Account> findAccountByType(Integer type);
}
