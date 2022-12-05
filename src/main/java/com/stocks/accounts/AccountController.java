package com.stocks.accounts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(path = "/createAccount")
    public ResponseEntity<?> addAccount(@RequestBody Account account) {
        try {    
            accountService.createAccount(account);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully created account");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to create account");
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody ArrayList<String> accountInfo) {
        String username; 
        String password;
        if (accountInfo.size() < 2) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Was not able to get username and password");
        }
        username = accountInfo.get(0);
        password = accountInfo.get(1);

        Account account = accountService.verifyAccount(username, password);

        if (account == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Was not able to verify the account with given credentials.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(account);
    } 

    

    @GetMapping(path = "/getAccounts")
    public ResponseEntity<?> getAccounts() {
        List<Account> accounts = accountService.getAccounts();

        return ResponseEntity.status(HttpStatus.OK).body(accounts);
    }

    @GetMapping(path = "/getAccount/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable int id) {
        Account account = accountService.getAccountById(id);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @DeleteMapping(path = "/deleteAccount/{id}")
    public void deleteAccount(@PathVariable int id) {
        try {
            accountService.deleteAccount(id);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }


}
