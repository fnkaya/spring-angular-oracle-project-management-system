package com.fnkaya.projectmanagement.security.service;

import com.fnkaya.projectmanagement.security.entity.Account;
import com.fnkaya.projectmanagement.security.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsManager {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return accountRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public void createUser(UserDetails userDetails) {
        Account account = (Account) userDetails;
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    @Override
    public void updateUser(UserDetails userDetails) {
        Account oldAccount = (Account) loadUserByUsername(userDetails.getUsername());
        Account newAccount = (Account) userDetails;
        newAccount.setId(oldAccount.getId());
        accountRepository.update(newAccount);
    }

    @Override
    public void deleteUser(String username) {
        accountRepository.deleteByUsername(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        Account account = (Account) loadUserByUsername(username);
        if (passwordEncoder.matches(oldPassword, account.getPassword())){
            account.setPassword(passwordEncoder.encode(newPassword));
            accountRepository.update(account);
        }
        else {
            throw new BadCredentialsException("Wrong old password is given!");
        }
    }

    @Override
    public boolean userExists(String s) {
        return false;
    }
}
