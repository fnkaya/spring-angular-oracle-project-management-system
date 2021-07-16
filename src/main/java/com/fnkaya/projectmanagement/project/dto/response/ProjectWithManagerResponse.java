package com.fnkaya.projectmanagement.project.dto.response;

import lombok.*;
import org.springframework.jdbc.core.RowMapper;

@Getter
@Setter
public class ProjectWithManagerResponse {

    private Long id;

    private String code;

    private String description;

    private String details;

    private Long manager_id;

    private String manager_name;

    private String manager_email;

    private int active;

    public static RowMapper<ProjectWithManagerResponse> rowMapper = (rs, rowNum) -> {
        ProjectWithManagerResponse response = new ProjectWithManagerResponse();
        response.setId(rs.getLong("project_id"));
        response.setCode(rs.getString("project_code"));
        response.setDescription(rs.getString("project_description"));
        response.setDetails(rs.getString("project_details"));
        response.setActive(rs.getInt("project_is_active"));
        response.setManager_id(rs.getLong("manager_id"));
        response.setManager_name(rs.getString("manager_name"));
        response.setManager_email(rs.getString("manager_email"));

        return response;
    };
}
