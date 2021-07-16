package com.fnkaya.projectmanagement.security.entity.enumeration;

public enum Role {

    ROLE_ADMIN("Admin"),
    ROLE_USER("User");

    private String value;

    Role(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
