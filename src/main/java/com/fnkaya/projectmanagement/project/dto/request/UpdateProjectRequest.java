package com.fnkaya.projectmanagement.project.dto.request;

import com.fnkaya.projectmanagement.common.dto.RequestDto;
import com.fnkaya.projectmanagement.project.entity.Project;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateProjectRequest implements RequestDto<Project> {

    @NotNull
    private Long id;

    @NotBlank
    private String description;

    @NotBlank
    private String details;

    @NotNull
    private Long manager_id;

    @NotNull
    private int active;

    @Override
    public Project getDomainObject() {
        Project project = new Project();
        project.setId(this.id);
        project.setDescription(this.description);
        project.setDetails(this.details);
        project.setManager_id(this.manager_id);
        project.setActive(this.active);

        return project;
    }
}
