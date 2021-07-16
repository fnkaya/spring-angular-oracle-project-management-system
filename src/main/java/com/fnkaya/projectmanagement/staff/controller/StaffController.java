package com.fnkaya.projectmanagement.staff.controller;

import com.fnkaya.projectmanagement.common.dto.Page;
import com.fnkaya.projectmanagement.issue.entity.enumeration.IssueStatus;
import com.fnkaya.projectmanagement.security.entity.enumeration.Role;
import com.fnkaya.projectmanagement.staff.dto.response.ManagerWithoutProjectResponse;
import com.fnkaya.projectmanagement.staff.dto.response.StaffResponse;
import com.fnkaya.projectmanagement.staff.dto.response.StaffWithAccountResponse;
import com.fnkaya.projectmanagement.staff.entity.enumeration.Position;
import com.fnkaya.projectmanagement.staff.service.StaffService;
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
@RequestMapping("api/staff")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class StaffController {

    private final StaffService staffService;

    @RequestMapping(value = "positions", method = RequestMethod.GET)
    public ResponseEntity<List<Map<String, String>>> getAllRole(){
        List<Map<String, String>> positionList = Arrays.stream(Position.values())
                .map(position -> Map.of("value", position.name(), "description", position.getValue()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(positionList);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void save(){

    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(){

    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(){

    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<StaffResponse> get(@PathVariable("id") Long id){
        StaffResponse staffResponse = new StaffResponse(staffService.get(id));

        return ResponseEntity.ok(staffResponse);
    }

    @RequestMapping(value = "with-account",method = RequestMethod.GET)
    public ResponseEntity<Page<StaffWithAccountResponse>> getAllWithAccount(Pageable pageable){
        Page<StaffWithAccountResponse> staffList = staffService.getAllWithAccount(pageable);

        return  ResponseEntity.ok(staffList);
    }

    @RequestMapping(value = "non-manager",method = RequestMethod.GET)
    public ResponseEntity<List<StaffResponse>> getAllNonManagerWithoutIssue(){
        List<StaffResponse> staffList = staffService.getAllNonManagerWithoutIssue().stream()
                .map(StaffResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(staffList);
    }

    @RequestMapping(value = "managers/without-project", method = RequestMethod.GET)
    public ResponseEntity<List<ManagerWithoutProjectResponse>> getAllManagerWithoutProject(){
        return ResponseEntity.ok(staffService.getAllManagerWithoutProject());
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ResponseEntity<Page<StaffWithAccountResponse>> search(@RequestParam("keyword") String keyword, Pageable pageable) {
        Page<StaffWithAccountResponse> staffList = staffService.search(keyword, pageable);

        return ResponseEntity.ok(staffList);
    }
}
