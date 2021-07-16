package com.fnkaya.projectmanagement.project.entity;

import lombok.*;
import org.springframework.jdbc.core.RowMapper;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Project{

    private Long id;

    private String code;

    private String description;

    private String details;

    private int active;

    private Long manager_id;

    public static RowMapper<Project> rowMapper = (rs, rowNum) -> {
        Project project = new Project();
        project.setId(rs.getLong("project_id"));
        project.setCode(rs.getString("project_code"));
        project.setDescription(rs.getString("project_description"));
        project.setDetails(rs.getString("project_details"));
        project.setManager_id(rs.getLong("manager_id"));
        project.setActive(rs.getInt("project_is_active"));

        return project;
    };
}
