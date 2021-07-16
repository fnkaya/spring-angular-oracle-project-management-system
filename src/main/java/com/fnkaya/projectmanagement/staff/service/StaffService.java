package com.fnkaya.projectmanagement.staff.service;

import com.fnkaya.projectmanagement.common.dto.Page;
import com.fnkaya.projectmanagement.staff.dto.response.ManagerWithoutProjectResponse;
import com.fnkaya.projectmanagement.staff.dto.response.StaffWithAccountResponse;
import com.fnkaya.projectmanagement.staff.entity.Staff;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffService{

    void save(Staff staff);

    void update(Staff staff);

    void delete(Long id);

    Staff get(Long id);

    Page<StaffWithAccountResponse> getAllWithAccount(Pageable pageable);

    List<ManagerWithoutProjectResponse> getAllManagerWithoutProject();

    Page<StaffWithAccountResponse> search(String keyword, Pageable pageable);

    List<Staff> getAllNonManagerWithoutIssue();
}
