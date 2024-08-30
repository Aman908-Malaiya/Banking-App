package net.javaguides.banking.service.impl;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.entity.Account;
import net.javaguides.banking.mapper.AccountMapper;
import net.javaguides.banking.repository.AccountRepository;
import net.javaguides.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service //to automatically create spring beans for this class
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;


    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
               Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccount(savedAccount);
    }

    @Override
    public AccountDto getAccountByid(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account Does Not Exists"));
        return AccountMapper.mapToAccount(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account Does Not Exists"));
       double total = account.getBalance() + amount;
       account.setBalance(total);
       Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccount(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account Does Not Exists"));
        if(account.getBalance() < amount){
            throw new RuntimeException("Insuffficient Amount");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account saveAccount = accountRepository.save(account);
        return AccountMapper.mapToAccount(saveAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
       List<Account> accounts = accountRepository.findAll();
      return accounts.stream().map((account) -> AccountMapper.mapToAccount(account))
               .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account Does Not Exists"));
        accountRepository.deleteById(id);

    }


}
