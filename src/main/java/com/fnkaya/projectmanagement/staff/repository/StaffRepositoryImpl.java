package com.fnkaya.projectmanagement.staff.repository;

import com.fnkaya.projectmanagement.common.dto.Page;
import com.fnkaya.projectmanagement.staff.dto.response.ManagerWithoutProjectResponse;
import com.fnkaya.projectmanagement.staff.dto.response.StaffWithAccountResponse;
import com.fnkaya.projectmanagement.staff.entity.Staff;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class StaffRepositoryImpl implements StaffRepository {

    private final JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall simpleJdbcCall;

    public StaffRepositoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

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
        String sql = "SELECT * FROM Staff WHERE staff_id = :id";

        return jdbcTemplate.queryForObject(sql, Staff.rowMapper, id);
    }

    @Override
    public List<Staff> getAllNonManagerWithoutIssue() {
        String sql = "SELECT * FROM Staff WHERE staff_position != 'PROJECT_MANAGER' AND staff_id NOT IN (SELECT assignee_id FROM Issue WHERE assignee_id IS NOT NULL)";

        return jdbcTemplate.query(sql, Staff.rowMapper);
    }

    @Override
    public Page<StaffWithAccountResponse> getAllWithAccount(Pageable pageable) {
        Object[] params = {pageable.getOffset(), pageable.getPageSize()};

        String sql = "SELECT * FROM get_staff_with_username_and_role OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        List<StaffWithAccountResponse> staffList = jdbcTemplate.query(sql, StaffWithAccountResponse.rowMapper, params);

        String countSql = "SELECT COUNT(*) FROM get_staff_with_username_and_role";
        Integer count = jdbcTemplate.queryForObject(countSql, Integer.class);

        return Page.of(pageable, count, staffList);
    }

    @Override
    public List<ManagerWithoutProjectResponse> getAllManagerWithoutProject(){
        String sql = "SELECT * FROM get_manager_without_project";

        return jdbcTemplate.query(sql, ManagerWithoutProjectResponse.rowMapper);
    }

    @Override
    public Page<StaffWithAccountResponse> search(String keyword, Pageable pageable) {
        Object[] params = {'%'+ keyword +'%', '%'+ keyword +'%', pageable.getOffset(), pageable.getPageSize()};

        String sql = "SELECT * FROM get_staff_with_username_and_role WHERE staff_name LIKE UPPER(?)" +
                                                                        "OR staff_email LIKE UPPER(?)" +
                                                                        "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        List<StaffWithAccountResponse> staffList = jdbcTemplate.query(sql, StaffWithAccountResponse.rowMapper, params);

        String countSql = "SELECT COUNT(*) FROM get_staff_with_username_and_role WHERE staff_name LIKE UPPER(?)" +
                                                                                    "OR staff_email LIKE UPPER(?)";

        Integer count = jdbcTemplate.queryForObject(countSql, Integer.class, new Object[] {'%'+ keyword +'%', '%'+ keyword +'%'});

        return Page.of(pageable, count, staffList);
    }
}
