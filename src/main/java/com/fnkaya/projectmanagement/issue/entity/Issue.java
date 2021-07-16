package com.fnkaya.projectmanagement.issue.entity;

import com.fnkaya.projectmanagement.issue.entity.enumeration.IssueStatus;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Issue {

    private Long id;

    private String code;

    private String description;

    private String details;

    private Date due_date;

    private IssueStatus status;

    private Long assignee_id;

    private Long project_id;
}
