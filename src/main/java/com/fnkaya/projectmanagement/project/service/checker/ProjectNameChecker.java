package com.fnkaya.projectmanagement.project.service.checker;

import com.fnkaya.projectmanagement.project.reposityory.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectNameChecker {

    private final ProjectRepository projectRepository;

    public Boolean isProjectNameAlreadyExist(String projectName){
//        return projectRepository.getByProjectName(projectName) != null;
        return null;
    }
}
