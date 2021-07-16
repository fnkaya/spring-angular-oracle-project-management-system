package com.fnkaya.projectmanagement.issue.repository;

import com.fnkaya.projectmanagement.common.dto.Page;
import com.fnkaya.projectmanagement.issue.dto.response.IssueHistoryResponse;
import com.fnkaya.projectmanagement.issue.dto.response.IssueResponse;
import com.fnkaya.projectmanagement.issue.dto.response.IssueWithAssigneeResponse;
import com.fnkaya.projectmanagement.issue.entity.Issue;
import com.fnkaya.projectmanagement.issue.entity.IssueHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class IssueRepositoryImpl implements IssueRepository{

    private final JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall simpleJdbcCall;

    public IssueRepositoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void save(Issue issue) {
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        simpleJdbcCall.withProcedureName("insert_issue");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("p_code", issue.getCode())
                .addValue("p_description", issue.getDescription())
                .addValue("p_details", issue.getDetails())
                .addValue("p_status", issue.getStatus())
                .addValue("p_due_date", issue.getDue_date())
                .addValue("p_project_id", issue.getProject_id());

        simpleJdbcCall.execute(params);
    }

    @Override
    public void update(Issue issue) {
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        simpleJdbcCall.withProcedureName("update_issue");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("p_code", issue.getCode())
                .addValue("p_description", issue.getDescription())
                .addValue("p_details", issue.getDetails())
                .addValue("p_due_date", issue.getDue_date())
                .addValue("p_status", issue.getStatus())
                .addValue("p_assignee_id", issue.getAssignee_id())
                .addValue("p_issue_id", issue.getId());

        simpleJdbcCall.execute(params);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Issue WHERE issue_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public IssueWithAssigneeResponse get(Long id) {
        String sql = "SELECT * FROM get_issue_with_assignee WHERE issue_id = ?";

        return jdbcTemplate.queryForObject(sql, IssueWithAssigneeResponse.rowMapper, id);
    }

    @Override
    public Page<IssueWithAssigneeResponse> getAll(Pageable pageable) {
        Object[] params = {pageable.getOffset(), pageable.getPageSize()};

        String sql = "SELECT * FROM get_issue_with_assignee OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        List<IssueWithAssigneeResponse> issueList = jdbcTemplate.query(sql, IssueWithAssigneeResponse.rowMapper, params);

        String countSql = "SELECT COUNT(*) FROM get_issue_with_assignee";
        Integer count = jdbcTemplate.queryForObject(countSql, Integer.class);

        return Page.of(pageable, count, issueList);
    }

    @Override
    public Page<IssueWithAssigneeResponse> search(String keyword, Pageable pageable){
        Object[] params = {'%' + keyword + '%', '%' + keyword + '%', '%' + keyword + '%', pageable.getOffset(), pageable.getPageSize()};

        String sql = "SELECT * FROM get_issue_with_assignee WHERE issue_code LIKE UPPER (?)" +
                                                                "OR issue_description LIKE UPPER (?)" +
                                                                "OR assignee_name LIKE UPPER (?)" +
                                                                "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        List<IssueWithAssigneeResponse> issueList = jdbcTemplate.query(sql, IssueWithAssigneeResponse.rowMapper, params);

        String countSql = "SELECT COUNT(*) FROM get_issue_with_assignee WHERE issue_code LIKE UPPER (?)" +
                                                                    "OR issue_description LIKE UPPER (?)" +
                                                                    "OR assignee_name LIKE UPPER (?)";
        Integer count = jdbcTemplate.queryForObject(countSql, Integer.class, new Object[] {'%' + keyword + '%', '%' + keyword + '%', '%' + keyword + '%'});

        return Page.of(pageable, count, issueList);
    }

    @Override
    public Page<IssueWithAssigneeResponse> getByProjectId(Long project_id, Pageable pageable) {
        Object[] params = {project_id, pageable.getOffset(), pageable.getPageSize()};

        String sql = "SELECT * FROM get_issue_with_assignee WHERE project_id = ?" +
                                                            "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        List<IssueWithAssigneeResponse> issueList = jdbcTemplate.query(sql, IssueWithAssigneeResponse.rowMapper, params);

        String countSql = "SELECT COUNT(*) FROM get_issue_with_assignee WHERE project_id = ?";
        Integer count = jdbcTemplate.queryForObject(countSql, Integer.class, project_id);

        return Page.of(pageable, count, issueList);
    }

    @Override
    public Page<IssueResponse> getByStaffId(Long staff_id, Pageable pageable) {
        Object[] params = {staff_id, pageable.getOffset(), pageable.getPageSize()};

        String sql = "SELECT * FROM Issue WHERE assignee_id = ?" +
                                            "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        List<IssueResponse> issueList = jdbcTemplate.query(sql, IssueResponse.rowMapper, params);

        String countSql = "SELECT COUNT(*) FROM Issue WHERE assignee_id = ?";
        Integer count = jdbcTemplate.queryForObject(countSql, Integer.class, staff_id);

        return Page.of(pageable, count, issueList);
    }

    @Override
    public Page<IssueWithAssigneeResponse> filter(String status, Pageable pageable) {
        Object[] params = {status, pageable.getOffset(), pageable.getPageSize()};

        String sql = "SELECT * FROM get_issue_with_assignee WHERE issue_status = ?" +
                                                            "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        List<IssueWithAssigneeResponse> issueList = jdbcTemplate.query(sql, IssueWithAssigneeResponse.rowMapper, params);

        String countSql = "SELECT COUNT(*) FROM get_issue_with_assignee WHERE issue_status = ?";
        Integer count = jdbcTemplate.queryForObject(countSql, Integer.class, status);

        return Page.of(pageable, count, issueList);
    }

    @Override
    public Page<IssueHistoryResponse> getIssueHistoryByIssueId(Long issue_id, Pageable pageable) {
        Object[] params = {issue_id, pageable.getOffset(), pageable.getPageSize()};

        String sql = "SELECT * FROM get_issue_history_with_assignee WHERE issue_id = ?" +
                                                                    "ORDER BY updated_time DESC " +
                                                                    "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        List<IssueHistoryResponse> issueHistoryList = jdbcTemplate.query(sql, IssueHistoryResponse.rowMapper, params);

        String countSql = "SELECT COUNT(*) FROM Issue_History WHERE issue_id = ?";
        Integer count = jdbcTemplate.queryForObject(countSql, Integer.class, issue_id);

        return Page.of(pageable, count, issueHistoryList);
    }
}
