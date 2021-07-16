package com.fnkaya.projectmanagement;

import com.fnkaya.projectmanagement.security.entity.Account;
import com.fnkaya.projectmanagement.security.entity.enumeration.Role;
import com.fnkaya.projectmanagement.security.repository.AccountRepository;
import com.fnkaya.projectmanagement.security.repository.AccountRepositoryImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class ProjectManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectManagementApplication.class, args);
    }
}
