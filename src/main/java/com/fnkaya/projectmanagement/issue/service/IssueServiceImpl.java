package com.fnkaya.projectmanagement.issue.service;

import com.fnkaya.projectmanagement.common.dto.Page;
import com.fnkaya.projectmanagement.issue.dto.response.IssueHistoryResponse;
import com.fnkaya.projectmanagement.issue.dto.response.IssueResponse;
import com.fnkaya.projectmanagement.issue.dto.response.IssueWithAssigneeResponse;
import com.fnkaya.projectmanagement.issue.entity.Issue;
import com.fnkaya.projectmanagement.issue.entity.IssueHistory;
import com.fnkaya.projectmanagement.issue.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService{

    private final IssueRepository issueRepository;


    @Override
    public void save(Issue issue) {
        this.issueRepository.save(issue);
    }

    @Override
    public void update(Issue issue) {
        this.issueRepository.update(issue);
    }

    @Override
    public void delete(Long id) {
        this.issueRepository.delete(id);
    }

    @Override
    public IssueWithAssigneeResponse get(Long id) {
        return issueRepository.get(id);
    }

    @Override
    public Page<IssueWithAssigneeResponse> getAll(Pageable pageable) {
        return issueRepository.getAll(pageable);
    }

   @Override
    public Page<IssueWithAssigneeResponse> search(String keyword, Pageable pageable){
        return issueRepository.search(keyword, pageable);
    }

    @Override
    public Page<IssueWithAssigneeResponse> filter(String status, Pageable pageable) {
        return issueRepository.filter(status, pageable);
    }

    @Override
    public Page<IssueWithAssigneeResponse> getByProjectId(Long project_id, Pageable pageable) {
        return issueRepository.getByProjectId(project_id, pageable);
    }

    @Override
    public Page<IssueHistoryResponse> getIssueHistoryByIssueId(Long issue_id, Pageable pageable) {
        return issueRepository.getIssueHistoryByIssueId(issue_id, pageable);
    }

    @Override
    public Page<IssueResponse> getByStaffId(Long staff_id, Pageable pageable) {
        return issueRepository.getByStaffId(staff_id, pageable);
    }
}
