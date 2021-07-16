package com.fnkaya.projectmanagement.issue.controller;

import com.fnkaya.projectmanagement.common.dto.Page;
import com.fnkaya.projectmanagement.issue.dto.request.AddIssueRequest;
import com.fnkaya.projectmanagement.issue.dto.request.UpdateIssueRequest;
import com.fnkaya.projectmanagement.issue.dto.response.IssueResponse;
import com.fnkaya.projectmanagement.issue.dto.response.IssueWithAssigneeResponse;
import com.fnkaya.projectmanagement.issue.entity.enumeration.IssueStatus;
import com.fnkaya.projectmanagement.issue.service.IssueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/issues")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class IssueController {

    private final IssueService issueService;

    @RequestMapping(value = "status", method = RequestMethod.GET)
    public ResponseEntity<List<Map<String, String>>> getAllStatus(){
        List<Map<String, String>> statusList = Arrays.stream(IssueStatus.values())
                .map(issueStatus -> Map.of("value", issueStatus.name(), "description", issueStatus.getValue()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(statusList);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody AddIssueRequest request){
        this.issueService.save(request.getDomainObject());
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody UpdateIssueRequest request) {
        this.issueService.update(request.getDomainObject());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        this.issueService.delete(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<IssueWithAssigneeResponse> get(@PathVariable("id") Long id){
        return ResponseEntity.ok(issueService.get(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<IssueWithAssigneeResponse>> getAll(Pageable pageable){
        return ResponseEntity.ok(issueService.getAll(pageable));
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ResponseEntity<Page<IssueWithAssigneeResponse>> search(@RequestParam(name = "keyword") String keyword, Pageable pageable){
        return ResponseEntity.ok(issueService.search(keyword, pageable));
    }

    @RequestMapping(value = "filter", method = RequestMethod.GET)
    public ResponseEntity<Page<IssueWithAssigneeResponse>> filter(@RequestParam("status") String status, Pageable pageable){
        return ResponseEntity.ok(issueService.filter(status, pageable));
    }

    @RequestMapping(value = "project-id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Page<IssueWithAssigneeResponse>> getByProjectId(@PathVariable("id") Long project_id, Pageable pageable){
        return ResponseEntity.ok(issueService.getByProjectId(project_id, pageable));
    }

    @RequestMapping(value = "staff-id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Page<IssueResponse>> getByStaffId(@PathVariable("id") Long staff_id, Pageable pageable){
        return ResponseEntity.ok(issueService.getByStaffId(staff_id, pageable));
    }
}
