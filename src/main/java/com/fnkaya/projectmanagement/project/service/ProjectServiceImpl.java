package com.fnkaya.projectmanagement.project.service;

import com.fnkaya.projectmanagement.project.dto.response.ProjectWithManagerResponse;
import com.fnkaya.projectmanagement.project.entity.Project;
import com.fnkaya.projectmanagement.project.reposityory.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public void save(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void update(Project project) {
        projectRepository.update(project);
    }

    @Override
    public void delete(Long id) {
        projectRepository.delete(id);
    }

    @Override
    public ProjectWithManagerResponse get(Long id) {
        return projectRepository.get(id);
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.getAll();
    }

    @Override
    public List<ProjectWithManagerResponse> getAllWithManager() {
        return projectRepository.getAllWithManager();
    }

    @Override
    public List<ProjectWithManagerResponse> search(String search) {
        return projectRepository.search(search);
    }
}
