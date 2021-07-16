package com.fnkaya.projectmanagement.staff.dto.response;

import com.fnkaya.projectmanagement.staff.entity.enumeration.Position;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.RowMapper;

@Getter
@Setter
public class StaffWithAccountResponse {

    private Long id;

    private String name;

    private String email;

    private Position position;

    private String username;

    private String role;

    public static RowMapper<StaffWithAccountResponse> rowMapper = (rs, rowNum) -> {
        StaffWithAccountResponse response = new StaffWithAccountResponse();

        response.setId(rs.getLong("staff_id"));
        response.setName(rs.getString("staff_name"));
        response.setEmail(rs.getString("staff_email"));
        response.setPosition(Position.valueOf(rs.getString("staff_position")));
        response.setUsername((rs.getString("account_username")));
        response.setRole((rs.getString("account_role")));

        return response;
    };
}
