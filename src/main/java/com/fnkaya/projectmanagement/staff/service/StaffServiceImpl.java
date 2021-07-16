package com.fnkaya.projectmanagement.staff.service;

import com.fnkaya.projectmanagement.common.dto.Page;
import com.fnkaya.projectmanagement.staff.dto.response.ManagerWithoutProjectResponse;
import com.fnkaya.projectmanagement.staff.dto.response.StaffWithAccountResponse;
import com.fnkaya.projectmanagement.staff.entity.Staff;
import com.fnkaya.projectmanagement.staff.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Override
    public void save(Staff staff) {

    }

    @Override
    public void update(Staff staff) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Staff get(Long id) {
        return staffRepository.get(id);
    }

    @Override
    public List<Staff> getAllNonManagerWithoutIssue() {
        return staffRepository.getAllNonManagerWithoutIssue();
    }

    @Override
    public Page<StaffWithAccountResponse> getAllWithAccount(Pageable pageable) {
        return staffRepository.getAllWithAccount(pageable);
    }

    @Override
    public List<ManagerWithoutProjectResponse> getAllManagerWithoutProject(){
        return staffRepository.getAllManagerWithoutProject();
    }

    @Override
    public Page<StaffWithAccountResponse> search(String keyword, Pageable pageable) {
        return staffRepository.search(keyword, pageable);
    }
}
