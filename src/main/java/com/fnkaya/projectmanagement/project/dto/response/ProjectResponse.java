package com.fnkaya.projectmanagement.project.dto.response;

import com.fnkaya.projectmanagement.project.entity.Project;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectResponse {

    private Long id;

    private String code;

    private String description;

    private String details;

    private int active;

    private Long manager_id;

    public ProjectResponse(Project project){
        this.id = project.getId();
        this.code = project.getCode();
        this.description = project.getDescription();
        this.details = project.getDetails();
        this.manager_id = project.getManager_id();
        this.active = project.getActive();
    }
}
