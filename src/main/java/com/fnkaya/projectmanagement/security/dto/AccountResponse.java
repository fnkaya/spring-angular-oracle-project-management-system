package com.fnkaya.projectmanagement.security.dto;

import com.fnkaya.projectmanagement.security.entity.Account;
import com.fnkaya.projectmanagement.security.entity.enumeration.Role;
import lombok.Data;

@Data
public class AccountResponse {

    private String username;

    private Role role;

    private String profile_image;

    private Long staff_id;

    public AccountResponse(Account account) {
        this.username = account.getUsername();
        this.role = account.getRole();
        this.profile_image = account.getProfile_image();
        this.staff_id = account.getStaff_id();
    }
}
