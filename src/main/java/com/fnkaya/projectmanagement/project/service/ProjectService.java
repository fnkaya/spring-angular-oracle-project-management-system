package com.fnkaya.projectmanagement.project.service;

import com.fnkaya.projectmanagement.project.dto.response.ProjectWithManagerResponse;
import com.fnkaya.projectmanagement.project.entity.Project;

import java.util.List;

public interface ProjectService{

    void save(Project project);

    void update(Project project);

    void delete(Long id);

    ProjectWithManagerResponse get(Long id);

    List<Project> getAll();

    List<ProjectWithManagerResponse> search(String description);

    List<ProjectWithManagerResponse> getAllWithManager();
}
