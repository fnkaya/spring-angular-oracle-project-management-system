package com.fnkaya.projectmanagement.staff.entity.enumeration;

public enum Position {

    PROJECT_MANAGER("Project Manager"),
    TEAM_LEADER("Team Leader"),
    ENGINEER("Software Engineer");

    private String value;

    Position(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
