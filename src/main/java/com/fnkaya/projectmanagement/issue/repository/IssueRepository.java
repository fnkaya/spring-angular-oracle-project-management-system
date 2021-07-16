package com.fnkaya.projectmanagement.issue.repository;

import com.fnkaya.projectmanagement.common.dto.Page;
import com.fnkaya.projectmanagement.issue.dto.response.IssueHistoryResponse;
import com.fnkaya.projectmanagement.issue.dto.response.IssueResponse;
import com.fnkaya.projectmanagement.issue.dto.response.IssueWithAssigneeResponse;
import com.fnkaya.projectmanagement.issue.entity.Issue;
import com.fnkaya.projectmanagement.issue.entity.IssueHistory;
import org.springframework.data.domain.Pageable;

public interface IssueRepository{

    void save(Issue issue);

    void update(Issue issue);

    void delete(Long id);

    IssueWithAssigneeResponse get(Long id);

    Page<IssueWithAssigneeResponse> getAll(Pageable pageable);

    Page<IssueWithAssigneeResponse> getByProjectId(Long project_id, Pageable pageable);

    Page<IssueWithAssigneeResponse> search(String keyword, Pageable pageable);

    Page<IssueWithAssigneeResponse> filter(String status, Pageable pageable);

    Page<IssueHistoryResponse> getIssueHistoryByIssueId(Long issue_id, Pageable pageable);

    Page<IssueResponse> getByStaffId(Long staff_id, Pageable pageable);
}

