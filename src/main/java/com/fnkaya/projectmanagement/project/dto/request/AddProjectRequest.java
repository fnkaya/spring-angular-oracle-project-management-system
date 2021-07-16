package com.fnkaya.projectmanagement.project.dto.request;

import com.fnkaya.projectmanagement.common.dto.RequestDto;
import com.fnkaya.projectmanagement.project.entity.Project;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddProjectRequest implements RequestDto<Project> {

    @NotBlank
    private String code;

    @NotBlank
    private String description;

    @NotBlank
    private String details;

    @NotNull
    private Long manager_id;

    @Override
    public Project getDomainObject() {
        Project project = new Project();
        project.setCode(this.code);
        project.setDescription(this.description);
        project.setDetails(this.details);
        project.setActive(1);
        project.setManager_id(this.manager_id);

        return project;
    }
}
