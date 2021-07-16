package com.fnkaya.projectmanagement.security.entity;

import com.fnkaya.projectmanagement.security.entity.enumeration.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@ToString
public class Account implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private String profile_image;

    private Role role;

    private Long staff_id;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static RowMapper<Account> rowMapper = (rs, rowNum) -> {
        Account account = new Account();
        account.setId(rs.getLong("account_id"));
        account.setUsername(rs.getString("account_username"));
        account.setPassword(rs.getString("account_password"));
        account.setRole(Role.valueOf(rs.getString("account_role")));
        account.setProfile_image(rs.getString("account_profile_image"));
        account.setStaff_id(rs.getLong("staff_id"));

        return account;
    };
}
