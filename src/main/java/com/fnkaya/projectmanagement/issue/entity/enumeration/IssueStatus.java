package com.fnkaya.projectmanagement.issue.entity.enumeration;

public enum IssueStatus {

    OPEN("Open"),
    CLOSED("Closed"),
    IN_PROGRESS("In progress"),
    RESOLVED("Resolved");

    private String value;

    IssueStatus(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
