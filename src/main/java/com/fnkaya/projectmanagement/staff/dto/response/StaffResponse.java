package com.fnkaya.projectmanagement.staff.dto.response;

import com.fnkaya.projectmanagement.staff.entity.Staff;
import com.fnkaya.projectmanagement.staff.entity.enumeration.Position;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffResponse {

    private Long id;

    private String name;

    private String email;

    private Position position;

    public StaffResponse(Staff staff){
        this.id = staff.getId();
        this.name = staff.getName();
        this.email = staff.getEmail();
        this.position = staff.getPosition();
    }
}
