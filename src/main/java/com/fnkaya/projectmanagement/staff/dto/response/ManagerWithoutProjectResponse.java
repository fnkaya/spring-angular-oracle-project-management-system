package com.fnkaya.projectmanagement.staff.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.RowMapper;

@Getter
@Setter
public class ManagerWithoutProjectResponse {

    private Long id;

    private String name;

    private String email;

    public static RowMapper<ManagerWithoutProjectResponse> rowMapper = (rs, rowNum) -> {
        ManagerWithoutProjectResponse response = new ManagerWithoutProjectResponse();

        response.setId(rs.getLong("staff_id"));
        response.setName(rs.getString("staff_name"));
        response.setEmail(rs.getString("staff_email"));

        return response;
    };
}
