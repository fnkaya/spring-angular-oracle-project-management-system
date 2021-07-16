package com.fnkaya.projectmanagement.security.dto;

import com.fnkaya.projectmanagement.security.entity.Account;
import com.fnkaya.projectmanagement.security.entity.enumeration.Role;
import com.fnkaya.projectmanagement.common.dto.RequestDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RegisterRequest implements RequestDto<Account> {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotNull
    private Long staff_id;

    @NotBlank
    private Role role;

    @Override
    public Account getDomainObject() {
        Account account = new Account();
        account.setUsername(this.username);
        account.setPassword(this.password);
        account.setStaff_id(this.staff_id);
        account.setRole(this.role);

        return account;
    }
}
