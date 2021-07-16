package com.fnkaya.projectmanagement.security.repository;

import com.fnkaya.projectmanagement.security.entity.Account;

public interface AccountRepository {
    Account findByUsername(String username);

    void save(Account account);

    void update(Account account);

    void deleteByUsername(String username);
}
