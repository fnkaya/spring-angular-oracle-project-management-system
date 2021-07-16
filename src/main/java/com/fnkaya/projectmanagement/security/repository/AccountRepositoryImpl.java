package com.fnkaya.projectmanagement.security.repository;

import com.fnkaya.projectmanagement.security.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private final JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall simpleJdbcCall;

    public AccountRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account findByUsername(String username) {
        String sql = "SELECT * FROM Account WHERE account_username = ?";

        return jdbcTemplate.queryForObject(sql, Account.rowMapper, username);
    }

    @Override
    public void save(Account account) {
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        simpleJdbcCall.withProcedureName("insert_account");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("p_username", account.getUsername())
                .addValue("p_password", account.getPassword())
                .addValue("p_role", account.getRole())
                .addValue("p_staff_id", account.getStaff_id());

        simpleJdbcCall.execute(params);
    }

    @Override
    public void update(Account account) {

    }

    @Override
    public void deleteByUsername(String username) {

    }
}
