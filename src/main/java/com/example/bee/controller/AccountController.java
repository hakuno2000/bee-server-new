package com.example.bee.controller;

import com.example.bee.entity.Account;
import com.example.bee.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class AccountController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Account account) {
        Account matchedAccount = accountService.login(account);
        if (matchedAccount == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(matchedAccount);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody Account account) {
        accountService.save(account);
        Account matchedAccount = accountService.findAccountByPhone(account.getPhone());
        if (matchedAccount == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(matchedAccount);
    }
    @GetMapping("/profile/{id}")
    public ResponseEntity<Object> getProfile(@PathVariable("id") long id) {
        return ResponseEntity.ok(accountService.findAccountById(id));
    }
    @GetMapping("/accounts")
    public ResponseEntity<Object> getAllAccounts() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/accounts/{type}")
    public Map<String, Object> getAllAccountsByType(@PathVariable("type") Integer type) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("accounts", accountService.getShipper(type));
        return map;
    }
}
