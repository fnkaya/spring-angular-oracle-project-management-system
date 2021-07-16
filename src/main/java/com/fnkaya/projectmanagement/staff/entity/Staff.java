package com.fnkaya.projectmanagement.staff.entity;

import com.fnkaya.projectmanagement.staff.entity.enumeration.Position;
import lombok.*;
import org.springframework.jdbc.core.RowMapper;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Staff {

    private Long id;

    private String name;

    private String email;

    private Position position;

    public static RowMapper<Staff> rowMapper = (rs, rowNum) -> {
        Staff staff = new Staff();
        staff.setId(rs.getLong("staff_id"));
        staff.setName(rs.getString("staff_name"));
        staff.setEmail(rs.getString("staff_email"));
        staff.setPosition(Position.valueOf(rs.getString("staff_position")));

        return staff;
    };
}
