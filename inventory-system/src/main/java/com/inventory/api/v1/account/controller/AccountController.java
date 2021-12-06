package com.inventory.api.v1.account.controller;

import com.inventory.api.v1.account.dto.AccountResponse;
import com.inventory.api.v1.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AccountController {
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountResponse>> getAllAccounts(){
        log.info("Request to get all available accounts");
        return ResponseEntity.ok(this.accountService.getAllAccounts());
    }
}
