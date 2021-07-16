package com.fnkaya.projectmanagement.project.controller;

import com.fnkaya.projectmanagement.project.dto.request.AddProjectRequest;
import com.fnkaya.projectmanagement.project.dto.request.UpdateProjectRequest;
import com.fnkaya.projectmanagement.project.dto.response.ProjectResponse;
import com.fnkaya.projectmanagement.project.dto.response.ProjectWithManagerResponse;
import com.fnkaya.projectmanagement.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/projects")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class ProjectController {

    private final ProjectService projectService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void save(@Valid @RequestBody AddProjectRequest addProjectRequest) {
        log.debug(addProjectRequest.toString());
        projectService.save(addProjectRequest.getDomainObject());
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@Valid @RequestBody UpdateProjectRequest updateProjectRequest) {
        log.debug(updateProjectRequest.toString());
        projectService.update(updateProjectRequest.getDomainObject());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        projectService.delete(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<ProjectWithManagerResponse> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(projectService.get(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<ProjectResponse>>> getAll() {
        List<ProjectResponse> projectList = projectService.getAll().stream()
                .map(ProjectResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(Map.of("projectList", projectList));
    }

    @RequestMapping(value = "with-manager", method = RequestMethod.GET)
    public ResponseEntity<List<ProjectWithManagerResponse>> getAllWithManager(){
        return ResponseEntity.ok(projectService.getAllWithManager());
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ResponseEntity<List<ProjectWithManagerResponse>> search(@RequestParam(name = "keyword") String keyword) {
        return ResponseEntity.ok(projectService.search(keyword));
    }
}
