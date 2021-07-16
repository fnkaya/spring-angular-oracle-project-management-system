package com.fnkaya.projectmanagement.issue.controller;

import com.fnkaya.projectmanagement.common.dto.Page;
import com.fnkaya.projectmanagement.issue.dto.response.IssueHistoryResponse;
import com.fnkaya.projectmanagement.issue.entity.IssueHistory;
import com.fnkaya.projectmanagement.issue.service.IssueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "api/issue-histories")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class IssueHistoryController {

    private final IssueService issueService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Page<IssueHistoryResponse>> getIssueHistoryByIssueId(@PathVariable("id") Long issue_id, Pageable pageable){
        return ResponseEntity.ok(issueService.getIssueHistoryByIssueId(issue_id, pageable));
    }
}
