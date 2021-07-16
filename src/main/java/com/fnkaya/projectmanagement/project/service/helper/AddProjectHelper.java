package com.fnkaya.projectmanagement.project.service.helper;

import com.fnkaya.projectmanagement.project.entity.Project;
import com.fnkaya.projectmanagement.project.reposityory.ProjectRepository;
import com.fnkaya.projectmanagement.project.service.checker.ProjectCodeChecker;
import com.fnkaya.projectmanagement.project.service.checker.ProjectNameChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddProjectHelper {

    private final ProjectRepository projectRepository;

    private final ProjectCodeChecker projectCodeChecker;

    private final ProjectNameChecker projectNameChecker;

    public void addProject(Project project){
        /*if (projectCodeChecker.isProjectCodeAlreadyExist(project.getCode()))
            throw new IllegalArgumentException("Proje code zaten var");

        if (projectNameChecker.isProjectNameAlreadyExist(project.getName()))
            throw new IllegalArgumentException("Proje adı zaten var");

        projectRepository.save(project);*/
    }
}
