package com.fnkaya.projectmanagement.project.service.checker;

import com.fnkaya.projectmanagement.project.reposityory.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectCodeChecker {

    private final ProjectRepository projectRepository;

    public Boolean isProjectCodeAlreadyExist(String projectCode){
//        return projectRepository.getByProjectCode(projectCode) != null;
        return null;
    }
}
