package net.javaguides.banking.controller;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.entity.Account;
import net.javaguides.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // ADd Account rest api
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //Get Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountByid(id);
        return ResponseEntity.ok(accountDto);
    }

    ///Dposite Rest APi
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,  @RequestBody Map<String, Double
            > request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
return ResponseEntity.ok(accountDto);
    }

    //Wiyhdraw REST  API
    @PutMapping("{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String, Double> request){
        double amount =  request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    //Get All Accounts REST API
    @GetMapping
    public ResponseEntity<List<AccountDto>> getALLAccounts(){
      List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    //Delete account REST APi
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account Is Deleted Successfully");
    }
}
